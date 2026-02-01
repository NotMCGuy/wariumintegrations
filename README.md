# Warium Integrations

JEI categories + in-game documentation for Warium/Crusty multiblock machines.

- **Mod ID:** `warium_integrations`
- **Minecraft:** 1.20.1
- **Forge:** 47.x

## What this mod does

### JEI integration (recommended)
Adds JEI recipe categories for these machine recipes:

- Foundry
- Assembly Machine
- Centrifuge
- Digester
- Circuit Fabricator
- (Assembly) Fabricator

This mod can run without JEI installed, but you won’t see the JEI categories.

### Patchouli manual (optional, client-side)
If Patchouli is present, the mod provides an in-game Patchouli book:

- **Book ID:** `warium_integrations:integration_manual`

To obtain the manual (creative/commands), run:

```mcfunction
/give @p patchouli:guide_book{patchouli:book:"warium_integrations:integration_manual"}
```

## Dependencies

**Required**

- Minecraft Forge 1.20.1 (Java 17)

**Optional / integrations**

- JEI (client): enables the recipe category UI.
- Patchouli (client): enables the in-game manual.

## Installation

1. Install Minecraft Forge for 1.20.1.
2. Download the mod JAR.
3. Drop it into your instance’s `mods/` folder.

## Documentation

- Implementation notes: [docs/Implementation Summary.md](docs/Implementation%20Summary.md)
- Patchouli add-ons: [docs/patchouli_addons.md](docs/patchouli_addons.md)
- KubeJS guide: [docs/KubeJS Recipe Guide.md](docs/KubeJS%20Recipe%20Guide.md)
  - Note: the repository currently contains the guide/examples, but the Java-side KubeJS integration is not present in `src/main/java` (and the Gradle KubeJS dependency is commented out). Treat this as **work-in-progress**.

Example scripts live in [docs/kubejs_examples/](docs/kubejs_examples/).

## Development

### Prerequisites

- Java 17

### Common Gradle commands (Windows)

- Generate IDE run configs:
  - IntelliJ: `gradlew.bat genIntellijRuns`
  - Eclipse: `gradlew.bat genEclipseRuns`
- Build: `gradlew.bat build`

## License

`mods.toml` declares the mod as **All Rights Reserved**.


## Credits

- JEI by mezz
- Patchouli by Vazkii
