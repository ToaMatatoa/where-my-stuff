import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
}

// The `libs` catalog accessor only exists in this script's own scope, not inside
// allprojects/subprojects blocks, so read what those blocks need up front.
val ktlintVersion = libs.versions.ktlint

// Static analysis is applied from here rather than per-module so that every module
// (and any future one) is checked with identical settings.
//
// ktlint owns formatting: indentation, imports, line width, trailing commas. It is
// driven entirely by .editorconfig — there are no rule settings in this file.
// detekt owns size/complexity limits, configured in config/detekt/detekt.yml.
//
// Both attach to `check`, and `build` depends on `check`, so `./gradlew build`
// fails on any violation.
allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    extensions.configure<KtlintExtension> {
        // The plugin bundles an older engine by default; pin the version we want.
        version.set(ktlintVersion)
        // Report every violation in one run instead of stopping at the first file.
        outputToConsole.set(true)

        filter {
            // Generated sources (Room, Compose Resources, KSP) are not ours to format.
            exclude { it.file.path.contains("${File.separator}build${File.separator}") }
        }
    }
}

// 63 of detekt's 188 rules — LongParameterList, UnusedImport, UnusedPrivateFunction,
// VarCouldBeVal and friends — implement RequiresAnalysisApi: they need a compiler
// classpath and are SKIPPED SILENTLY without one. Only detekt's per-compilation tasks
// get a classpath, so the gate has to run one of those, not the classpath-free
// aggregate `detekt` task.
//
// One task per module is enough, and it has to be a JVM-backed one: each covers
// commonMain plus its own platform sources, so running more than one would report
// every finding in shared code several times over. Kotlin/Native compilations get no
// such task at all — detekt's type resolution is JVM-only.
val typeResolvedDetektTasks = setOf("detektMainAndroid", "detektDebugAndroid")

subprojects {
    apply(plugin = "dev.detekt")

    extensions.configure<DetektExtension> {
        // Everything under src/; the aggregate task then hands commonMain and
        // androidMain back to the type-resolved task below.
        source.setFrom(layout.projectDirectory.dir("src"))

        // Our detekt.yml only lists the rules we deliberately changed; everything
        // else falls back to detekt's defaults.
        buildUponDefaultConfig = true
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        // Report paths relative to the repo root so they match between local and CI.
        basePath = rootProject.layout.projectDirectory
        parallel = true
    }

    tasks.withType<Detekt>().configureEach {
        // A compilation's sources include generated code — Compose Resources
        // accessors, Room's KSP output — which we neither wrote nor can fix.
        // A glob ("**/build/**") does not work here: detekt hands the task a flat
        // file collection, so there is no relative path for the pattern to match.
        val buildDirSegment = "${File.separator}build${File.separator}"
        exclude { it.file.absolutePath.contains(buildDirSegment) }
    }

    tasks.named<Detekt>("detekt") {
        // commonMain and androidMain are analysed with type resolution by the task
        // below, so skip them here to avoid reporting shared code twice. Listing what
        // to skip rather than what to keep means every other source set — jvmMain,
        // desktopMain, iosMain, the test sets, and any target added later — is picked
        // up automatically instead of being silently left unchecked.
        val typeResolvedSourceSets = listOf("commonMain", "androidMain")
            .map { "${File.separator}src${File.separator}$it${File.separator}" }
        exclude { element ->
            typeResolvedSourceSets.any { element.file.absolutePath.contains(it) }
        }
    }

    // `check` already depends on the aggregate task; add the type-resolved one
    // alongside it. They must be siblings, not chained: Gradle skips the dependents
    // of a failed task, so hanging one off the other would hide every iOS/desktop
    // finding behind the first shared-code failure.
    // `matching {}.configureEach {}` rather than `named("check")`: this block runs
    // while the root script is evaluated, before the modules apply the Kotlin and
    // Android plugins that create `check`.
    tasks.matching { it.name == LifecycleBasePlugin.CHECK_TASK_NAME }.configureEach {
        dependsOn(tasks.withType<Detekt>().matching { it.name in typeResolvedDetektTasks })
    }
}

// `./gradlew build` already runs both tools through `check`, but it compiles and
// tests everything first. This is the fast entry point for CI and for a pre-push
// hook: static analysis only, nothing tested.
//
// Registered in every project, so `./gradlew staticAnalysis` matches the task by
// name in all of them — the root included, which is what covers the root build
// script itself. Add --continue to see every module's findings in one run instead
// of stopping at the first failure.
allprojects {
    tasks.register("staticAnalysis") {
        group = LifecycleBasePlugin.VERIFICATION_GROUP
        description = "Runs ktlint and detekt over this module."
        dependsOn(tasks.named("ktlintCheck"))
        dependsOn(
            tasks.withType<Detekt>().matching {
                it.name == "detekt" || it.name in typeResolvedDetektTasks
            },
        )
    }
}
