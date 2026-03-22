# CLAUDE.md

## Project Overview

This is **cocos2d-android**, a Java port of the cocos2d 2D game framework for Android. It is a fork of the original cocos2d-android (v0.82) by Zhou Weikuan, extended and maintained by eBrothers. The repo contains the core library, a test suite, and three sample game projects.

- **Language:** Java (Android SDK)
- **Min SDK:** Android 1.6 (API level 4) for the library; sample games target API 7-8
- **License:** BSD (Sticky Coding)
- **Package:** `org.cocos2d`

## Repository Structure

```
cocos2d-android/       # Core library (current version) — the main framework
cocos2d-android_v1/    # Older snapshot of the library (v1)
cocos2d-test/          # Test/demo app exercising framework features
ForestRunner/          # Sample game — endless runner (com.ebrothers.forestrunner)
LineRunner/            # Sample game — line-based runner (com.ebrothers.linerunner)
MX Mayhem Lite/        # Sample game — motocross game (com.ebtorhers.motox)
```

### Core Library Packages (`cocos2d-android/src/org/cocos2d/`)

| Package | Purpose |
|---------|---------|
| `actions` | Sprite actions (move, rotate, scale, sequences) |
| `config` | Framework configuration |
| `events` | Touch and accelerometer event handling |
| `grid` | Grid-based effects |
| `layers` | Layer abstractions (CCLayer, CCColorLayer, etc.) |
| `levelhelper` | LevelHelper integration for level editing |
| `menus` | Menu system (CCMenu, CCMenuItem variants) |
| `nodes` | Scene graph nodes (CCNode, CCScene, CCSprite, etc.) |
| `opengl` | OpenGL ES rendering utilities |
| `particlesystem` | Particle effects |
| `protocols` | Interfaces / protocol definitions |
| `sound` | Audio playback |
| `transitions` | Scene transition effects |
| `types` | Core data types (CGPoint, CGSize, CGRect, etc.) |
| `utils` | Utility classes |

The library also bundles `com.badlogic.gdx` math and Box2D physics bindings.

## Build Commands

The projects use Eclipse-era Android project structure (no Gradle). Build with Apache Ant:

```bash
# Set up local SDK path (required before building)
echo "sdk.dir=/path/to/android-sdk" > local.properties

# Build the core library
cd cocos2d-android
ant build

# Build and install the test app on a connected device
cd cocos2d-test
ant install
```

Maven is also configured for the core library:

```bash
cd cocos2d-android
mvn compile
```

The prebuilt library JAR is at `cocos2d-android/bin/cocos2d-android.jar`. Sample games include this JAR in their `libs/` directories.

## Test Commands

The `cocos2d-test` project contains visual/interactive demo tests (not unit tests). Tests cover:

- Actions, EaseActions, ActionManager
- Transitions, Effects, EffectsAdvanced
- Sprites, Atlas, Fonts, Menus
- Particles, TileMaps, Parallax
- Box2D physics, PhysicsEditor, DrawPrimitives
- Scene management, Scheduler, MotionStreak, RotateWorld

Run via `ant install` in `cocos2d-test/` on a connected Android device or emulator.

## Key Architecture Notes

- Follows the cocos2d scene-graph pattern: `CCDirector` manages a stack of `CCScene` objects, each containing a tree of `CCNode` children.
- Rendering is OpenGL ES-based, driven by `CCGLSurfaceView`.
- Actions are composable objects attached to nodes (e.g., `CCSequence`, `CCSpawn`, `CCRepeatForever`).
- Transitions animate between scenes using effects like fade, flip, slide, and zoom.
- LevelHelper integration (C++ headers in `LevelHelper-c++/`) supports loading levels designed in the LevelHelper editor.
- Box2D physics from libgdx is bundled for physics-enabled games.

## Coding Conventions

- Class names use the `CC` prefix following cocos2d convention (e.g., `CCNode`, `CCSprite`, `CCDirector`).
- Core types mirror Objective-C cocos2d naming: `CGPoint`, `CGSize`, `CGRect`, `ccColor3B`, `ccColor4F`.
- Package structure follows standard Java conventions under `org.cocos2d.*`.
- Eclipse project files (`.project`, `.classpath`, `.settings/`) are checked in — this predates Android Studio / Gradle.
- No automated test framework; testing is visual via the `cocos2d-test` demo app.
