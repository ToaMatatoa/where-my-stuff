# Where My Stuff

A Kotlin Multiplatform home-inventory app for people who own more boxes than memory. Register the
places in your home, break them into sub-places, and record what lives in each one — then find any
item again without opening every drawer.

Runs from a single shared codebase on **Android, iOS and Desktop (JVM)**.

## Features

- **Three-level hierarchy** — Place → Sub-place → Stuff (e.g. *Garage → Blue shelf → Winter tyres*)
- Add, rename and delete at every level, with confirmation dialogs for destructive actions
- Per-sub-place descriptions and comments
- Fully offline — everything is stored locally, no account, no backend
- Native splash screen on each platform

## Architecture

Layered multiplatform modules with a strictly enforced dependency direction:

```
composeApp  ──►  designSystem
     │
     └────────►  domain  ──►  core
```

| Module | Responsibility | Depends on |
|---|---|---|
| `composeApp` | Compose Multiplatform UI, ViewModels, navigation; entry point for every target | `designSystem`, `domain` |
| `domain` | Business logic — use cases and domain models | `core` |
| `core` | Data layer — Room database, entities, data sources, repositories | — |
| `designSystem` | Colors, typography, `AppTheme`, reusable UI elements | — |

The UI never touches the database directly: screens talk to ViewModels, ViewModels call use cases,
use cases go through repository interfaces that `core` implements.

## Tech stack

| | |
|---|---|
| Language | Kotlin 2.4 |
| UI | Compose Multiplatform, Material 3 |
| Navigation | Navigation 3 (`org.jetbrains.androidx.navigation3`, the Compose Multiplatform port) |
| DI | Koin |
| Persistence | Room (KMP) with the bundled SQLite driver |
| Async | Coroutines + Flow |
| Serialization | kotlinx.serialization |

### Platform-specific code

Most of the app is in `commonMain`. Where a platform genuinely differs, the split is done with
`expect`/`actual` rather than by duplicating features — the database builder is the clearest example:

```
core/src/androidMain/…/AndroidDatabase.kt
core/src/iosMain/…/IOSDatabase.kt
core/src/jvmMain/…/DesktopDatabase.kt
```

`iosApp/` holds a thin SwiftUI host for the iOS entry point.

## Running

```bash
./gradlew :composeApp:assembleDebug     # Android
./gradlew :composeApp:run               # Desktop (JVM)
```

For iOS, open `iosApp/` in Xcode and run from there.

## Tests

```bash
./gradlew :composeApp:testDebugUnitTest      # Android unit tests
./gradlew :composeApp:desktopTest            # Desktop
./gradlew :composeApp:iosSimulatorArm64Test  # iOS simulator
./gradlew :core:jvmTest                      # data layer, incl. database migration test
```

## License

Apache License 2.0 — see [LICENSE](./LICENSE).
