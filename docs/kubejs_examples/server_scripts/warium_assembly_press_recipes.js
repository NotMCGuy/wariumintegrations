// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Assembly Machine - Press Head
// Usage: Press: ingots -> plates
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:assembly_press';
  const ID_PREFIX = 'kubejs:warium/assembly_press/';

  const press = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  press('crusty_chunks:steel_ingot', 'crusty_chunks:steelplate', 'steel_plate_from_steel_ingot');
  press('minecraft:copper_ingot', 'crusty_chunks:copper_plate', 'copper_plate_from_copper_ingot');
  press('crusty_chunks:brass_ingot', 'crusty_chunks:brass_plate', 'brass_plate_from_brass_ingot');
  press(
    'immersiveengineering:ingot_aluminum',
    'crusty_chunks:aluminum_plate',
    'aluminum_plate_from_ie_aluminum_ingot'
  );
});
