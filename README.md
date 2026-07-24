This is a Kotlin Multiplatform project targeting Android, iOS, Desktop (JVM).

* [/composeApp](./composeApp/src) is a single module holding all the shared code, including UI built with
  Compose Multiplatform. It contains several source sets:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that's common for all targets.
  - Platform folders hold Kotlin code compiled for only the platform indicated by the folder name.
    For example, [iosMain](./composeApp/src/iosMain/kotlin) is where you'd put iOS-specific calls (e.g.
    Apple's CoreCrypto), [androidMain](./composeApp/src/androidMain/kotlin) holds the Android `MainActivity`
    and manifest, and [desktopMain](./composeApp/src/desktopMain/kotlin) holds the Desktop (JVM) entry point.

* [/iosApp](./iosApp/iosApp) contains the iOS application entry point. Even though the UI is shared with
  Compose Multiplatform, this thin SwiftUI host is required, and it's where you'd add any native SwiftUI code.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :composeApp:assembleDebug`
- Desktop app: `./gradlew :composeApp:run`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :composeApp:testDebugUnitTest`
- Desktop tests: `./gradlew :composeApp:desktopTest`
- iOS tests: `./gradlew :composeApp:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
