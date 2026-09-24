<div align="center">

# 🌅 20 Minutes Till Dawn

**A top-down survival shooter built in Java with libGDX — survive waves of monsters until sunrise, levelling up as the horde thickens.**

[![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![libGDX](https://img.shields.io/badge/libGDX-1.13-E74A45?style=flat-square)](https://libgdx.com/)
[![Database](https://img.shields.io/badge/SQLite-JDBC-003B57?style=flat-square&logo=sqlite&logoColor=white)](https://www.sqlite.org/)
[![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle)](https://gradle.org/)

*A reimplementation of the game by flanne — built for Advanced Programming, Sharif University of Technology*

</div>

---

## Overview

You pick a hero, pick a gun, and hold out against an escalating swarm until the
clock runs out. Kills drop XP; XP levels you up; every level you choose one of
three random upgrades. Halfway through the run a boss spawns. The pressure curve
is the whole game.

Around that core sits a complete application: accounts with password recovery,
a persistent scoreboard, remappable controls, two languages, and save/resume of
a run in progress.

## Gameplay

### Heroes

Each hero trades health against speed.

| Hero | HP | Speed |
|---|:---:|:---:|
| **Diamond** | 7 | 1 |
| **Lilith** | 5 | 3 |
| **Shana** | 4 | 4 |
| **Scarlett** | 3 | 5 |
| **Dasher** | 2 | 10 |

### Weapons

| Weapon | Damage | Projectiles | Ammo | Fire rate | Reload |
|---|:---:|:---:|:---:|:---:|:---:|
| **Revolver** | 20 | 1 | 6 | 0.3s | 1.0s |
| **Shotgun** | 10 | 4 | 2 | 0.5s | 1.0s |
| **SMG** | 8 | 1 | 24 | 0.07s | 2.0s |

### The horde

| Enemy | HP | Speed | Behaviour |
|---|:---:|:---:|---|
| 🌳 **Tree** | ∞ | — | Forty of them, scattered at spawn. Immovable cover that also blocks you. |
| 🧠 **Brain Monster** | 25 | 2.2 | Spawns every 3s, in counts that scale with elapsed time. |
| 👁️ **Eye Bat** | 50 | 1.5 | Joins at the quarter mark, ramping up as the run goes on. |
| 💀 **Elder** | 400 | 1.0 | The boss. Spawns at the halfway point, once per run. |

### Upgrades

On each level-up you choose one of three offered abilities:

| Ability | Effect |
|---|---|
| **Vitality** | More maximum health |
| **Damager** | More weapon damage |
| **Procrease** | More projectiles per shot |
| **Ammocrease** | Larger magazine |
| **Speedy** | Faster movement |

## Features

| | |
|---|---|
| 🎯 **Auto-aim toggle** | Snap targeting on or off mid-run. |
| ⏸️ **Pause & resume** | Full pause dialog; runs serialize to disk and reload intact. |
| 👤 **Accounts** | Sign-up, login, guest mode, avatar selection, and password recovery through a security question. |
| 🗄️ **SQLite persistence** | Users, scores, kill counts and best survival times stored via JDBC — not a flat file. |
| 🏆 **Scoreboard** | Ranked across all registered players. |
| ⌨️ **Remappable controls** | Every binding configurable from the settings menu. |
| 🌍 **Localization** | English and French, switchable at runtime. |
| 🎨 **Animated sprites** | Per-hero idle and run cycles, enemy animations, weapon reload animations, death effects. |

## Architecture

Strict **MVC**, which is what the assignment was really testing:

```
core/src/main/java/com/untillDawn/
├── Model/                    # State — knows nothing about rendering
│   ├── GameModels/           # Player, Enemy, Bullet, Weapon, XpSeed, Game
│   │   └── Enums/            # HeroType, WeaponType, EnemyType, Ability
│   ├── Enums/                # Language, Keybinding, AllTexts, AllColors
│   ├── App.java              # Global application state
│   ├── User.java             # Account model
│   ├── Settings.java         # Persisted preferences
│   ├── DatabaseManager.java  # SQLite/JDBC layer
│   └── AppAssetManager.java  # Singleton texture & animation cache
├── View/                     # Scene2D screens and dialogs — no game logic
└── Control/                  # Input handling and per-frame updates
    └── CameControllers/      # GameController, EnemyController, BulletController,
                              # PlayerController, WeaponController, WorldController
```

Two details worth calling out:

**Enums carry their own data and assets.** `HeroType`, `WeaponType` and
`EnemyType` hold stats *and* resolve their own textures and animations in the
constructor, via a singleton `AppAssetManager`. Adding a new weapon is one enum
line, not a new class.

**The game loop is split by concern.** `GameController` orchestrates, but enemy
spawning, bullet physics, player movement and weapon state each live in their own
controller with their own update step.

## Build & run

Requires a **JDK 11 or newer** to build; the project compiles to Java 8 bytecode. Gradle is bundled via the wrapper.

```bash
./gradlew lwjgl3:run
```

Package a runnable jar:

```bash
./gradlew lwjgl3:jar
# → lwjgl3/build/libs/
```

<details>
<summary><strong>Other Gradle tasks</strong></summary>

| Task | Does |
|---|---|
| `build` | Compile and archive every module |
| `clean` | Remove build folders |
| `test` | Run unit tests |
| `--daemon` | Reuse the Gradle daemon (faster repeat builds) |
| `--offline` | Build from cached dependencies |

Module-scoped tasks take a `name:` prefix — `core:clean` cleans only `core`.

</details>

## Modules

| Module | Contains |
|---|---|
| `core` | All game logic, shared across platforms |
| `lwjgl3` | Desktop launcher (LWJGL3) |

---

<sub>Built with <a href="https://libgdx.com/">libGDX</a>, scaffolded with <a href="https://github.com/libgdx/gdx-liftoff">gdx-liftoff</a>.</sub>
