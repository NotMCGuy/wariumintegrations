# Warium Integrations - KubeJS Recipe Types

This document explains how to add and modify recipes for all Warium machines using KubeJS.

## Overview

Warium Integrations now supports custom recipe definitions for all machine types via KubeJS. This allows server admins and modpack creators to easily customize recipes without modifying Java code.

Note: Most machines use the `warium:*` recipe namespace. The Mechanical Fabricator uses `warium_integrations:mechanical_fabricator`.

### Supported Machines

- **Foundry** - Template + Material → Result
- **Assembly Machine (Press)** - Ingot → Plate
- **Assembly Machine (Shear)** - Cuts plates/components into wires, gears
- **Assembly Machine (Extruder)** - Draws gears, wires, coils, wood components
- **Assembly Machine (Bore)** - Hollows barrels/projectiles, bores cast parts
- **Centrifuge** - Dust → Tiny Dust
- **Digester** - Dust → Filtered Dust
- **Circuit Fabricator** - Unfabricated Component → Fabricated Component
- **Mechanical Fabricator** - Ingot/Block → Component

---

## Basic Recipe Format

All Warium recipes use the standard KubeJS format:

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:RECIPE_TYPE',
    // inputs and outputs here
  })
  // Strongly recommended: set a stable ID so you can remove/override the recipe later.
  .id('kubejs:warium/<machine>/<your_recipe_name>');
});
```

### Recommended Conventions (Matches the Example Scripts)

- Prefer a per-file `TYPE` constant and `ID_PREFIX` constant.
- Wrap repeated patterns in a small helper function (keeps examples short and consistent).
- Use the `kubejs:warium/...` ID namespace for `warium:*` machines.
- Use the `kubejs:warium_integrations/...` ID namespace for `warium_integrations:*` machines.

Example helper pattern:

```javascript
ServerEvents.recipes(event => {
  const TYPE = 'warium:assembly_press';
  const ID_PREFIX = 'kubejs:warium/assembly_press/';

  const press = (input, resultItem, idSuffix) => {
    event
      .custom({ type: TYPE, input, result: { item: resultItem } })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  press('minecraft:copper_ingot', 'crusty_chunks:copper_plate', 'copper_plate_from_copper_ingot');
});
```

---

## Machine Recipes

### Foundry

**Type:** `warium:foundry`

**Inputs:**
- `template` - The foundry template item (ingredient)
- `material` - The material to forge (ingredient)

**Output:**
- `result` - The resulting item (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:foundry',
    template: 'crusty_chunks:component_foundry_template',
    material: 'immersiveengineering:ingot_steel',
    result: { item: 'crusty_chunks:cast_component' }
  }).id('kubejs:warium/foundry/cast_component_from_steel_ingot');
});
```

---

### Assembly Machine - Press

**Type:** `warium:assembly_press`

**Inputs:**
- `input` - Ingot to press (ingredient)

**Output:**
- `result` - Resulting plate (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:assembly_press',
    input: 'crusty_chunks:steel_ingot',
    result: { item: 'crusty_chunks:steelplate' }
  }).id('kubejs:warium/assembly_press/steel_plate_from_steel_ingot');
});
```

---

### Assembly Machine - Shear

**Type:** `warium:assembly_shear`

**Inputs:**
- `input` - Component/plate to cut (ingredient)

**Output:**
- `result` - Cut result (wire/gear/trim) (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:assembly_shear',
    input: 'crusty_chunks:copper_plate',
    result: { item: 'crusty_chunks:copper_wire' }
  }).id('kubejs:warium/assembly_shear/copper_wire_from_copper_plate');
});
```

---

### Assembly Machine - Extruder

**Type:** `warium:assembly_extruder`

**Inputs:**
- `input` - Component to extrude (ingredient)

**Output:**
- `result` - Extruded result (gear/wire/coil/wood component) (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:assembly_extruder',
    input: 'minecraft:oak_planks',
    result: { item: 'crusty_chunks:wood_component' }
  }).id('kubejs:warium/assembly_extruder/wood_component_from_oak_planks');
});
```

---

### Assembly Machine - Bore (Lathe)

**Type:** `warium:assembly_bore`

**Inputs:**
- `input` - Component to bore/lathe (ingredient)

**Output:**
- `result` - Bored/lathed result (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:assembly_bore',
    input: 'crusty_chunks:bent_component',
    result: { item: 'crusty_chunks:component_bore_result' }
  }).id('kubejs:warium/assembly_bore/component_bore_result_from_bent_component');
});
```

---

### Centrifuge

**Type:** `warium:centrifuge`

**Inputs:**
- `input` - Dust to centrifuge (ingredient)

**Output:**
- `result` - Tiny dust result (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:centrifuge',
    input: 'crusty_chunks:filtered_pyrochlore_dust',
    result: { item: 'crusty_chunks:niobium_tiny_dust' }
  }).id('kubejs:warium/centrifuge/niobium_tiny_dust_from_filtered_pyrochlore_dust');
});
```

---

### Digester (Chemical Digester)

**Type:** `warium:digester`

**Inputs:**
- `input` - Dust to digest (ingredient)

**Output:**
- `result` - Processed dust/concentrate (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:digester',
    input: 'crusty_chunks:pyrochlore_dust',
    result: { item: 'crusty_chunks:filtered_pyrochlore_dust' }
  }).id('kubejs:warium/digester/filtered_pyrochlore_dust_from_pyrochlore_dust');
});
```

---

### Circuit Fabricator

**Type:** `warium:circuit_fabricator`

**Inputs:**
- `input` - Unfabricated component (ingredient)

**Output:**
- `result` - Fabricated component (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:circuit_fabricator',
    input: 'crusty_chunks:unfabricated_tech_component',
    result: { item: 'crusty_chunks:tech_component' }
  }).id('kubejs:warium/circuit_fabricator/tech_component_from_unfabricated_tech_component');
});
```

---

### Mechanical Fabricator

**Type:** `warium_integrations:mechanical_fabricator`

**Inputs:**
- `input` - Ingot or block to fabricate (ingredient)

**Output:**
- `result` - Fabricated component (itemstack)

**Example:**

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium_integrations:mechanical_fabricator',
    input: 'crusty_chunks:advanced_alloy_ingot',
    result: { item: 'crusty_chunks:advanced_alloy_component' }
  }).id('kubejs:warium_integrations/mechanical_fabricator/advanced_alloy_component_from_advanced_alloy_ingot');
});
```

---

## Advanced Features

### Using Item Counts

You can specify the output count by using the `count` property:

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:foundry',
    template: 'crusty_chunks:small_projectile_foundry_template',
    material: 'immersiveengineering:nugget_lead',
    result: { item: 'crusty_chunks:small_projectile', count: 3 }
  }).id('kubejs:warium/foundry/small_projectile_x3_from_lead_nugget');
});
```

### Using Tags

You can use item tags as inputs:

```javascript
ServerEvents.recipes(event => {
  event.custom({
    type: 'warium:assembly_press',
    input: '#forge:ingots/steel',
    result: { item: 'crusty_chunks:steelplate' }
  }).id('kubejs:warium/assembly_press/steel_plate_from_forge_steel_ingots');
});
```

### Removing Recipes

To remove a recipe, use the standard KubeJS removal:

```javascript
event.remove({ output: 'crusty_chunks:steelplate' });
```

---

## Example Scripts

Reference copies of the example scripts live in `docs/kubejs_examples/`.

The scripts that actually run in-game must be placed under `kubejs/*_scripts/` (for example `kubejs/server_scripts/`).

- `warium_foundry_recipes.js`
- `warium_assembly_press_recipes.js`
- `warium_assembly_shear_recipes.js`
- `warium_assembly_extruder_recipes.js`
- `warium_assembly_bore_recipes.js`
- `warium_centrifuge_recipes.js`
- `warium_digester_recipes.js`
- `warium_circuit_fabricator_recipes.js`
- `warium_mechanical_fabricator_recipes.js`

---

## Tips

1. **Item IDs**: Always use the full item ID including namespace (e.g., `crusty_chunks:steel_ingot`)
2. **Ingredients**: Use either item IDs or tags as ingredients
3. **Recipe IDs**: Always use `.id('kubejs:...')` so you can reliably remove/override recipes later
3. **Script Organization**: Keep recipes for each machine type in separate files for clarity
4. **Testing**: Use `/reload` to test recipe changes without restarting the server

---

## Troubleshooting

- **Recipe not showing up?** Check that the recipe type name matches exactly (case-sensitive)
- **Item not found?** Verify the item ID exists in your modpack
- **Still not working?** Check the server logs for KubeJS errors
