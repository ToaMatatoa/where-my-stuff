import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
}

kotlin {
    jvm()
    iosArm64()
    iosSimulatorArm64()

    android {
        namespace = "com.matatoa.wheremystuff.domain"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core"))
        }
        commonTest.dependencies {
            // koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            implementation(libs.kotlin.test)
        }
    }
}
