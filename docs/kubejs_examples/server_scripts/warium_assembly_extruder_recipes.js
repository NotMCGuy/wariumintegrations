// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Assembly Machine - Extruder Head
// Usage: Extruder: draws gears, wires, coils, or wood components
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:assembly_extruder';
  const ID_PREFIX = 'kubejs:warium/assembly_extruder/';

  const extruder = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  extruder('crusty_chunks:cast_component', 'crusty_chunks:steel_gear', 'steel_gear_from_cast_component');
  extruder('minecraft:oak_planks', 'crusty_chunks:wood_component', 'wood_component_from_oak_planks');
  extruder('crusty_chunks:steel_ingot', 'crusty_chunks:steel_wire', 'steel_wire_from_steel_ingot');
  extruder('crusty_chunks:steel_wire', 'crusty_chunks:steel_spring', 'steel_spring_from_steel_wire');
  extruder('minecraft:copper_ingot', 'crusty_chunks:copper_wire', 'copper_wire_from_copper_ingot');
  extruder('crusty_chunks:copper_wire', 'crusty_chunks:copper_coil', 'copper_coil_from_copper_wire');
});
