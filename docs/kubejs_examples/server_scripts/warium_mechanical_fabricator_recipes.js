// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Mechanical Fabricator
// Usage: Fabricates components from ingots and blocks
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium_integrations:mechanical_fabricator';
  const ID_PREFIX = 'kubejs:warium_integrations/mechanical_fabricator/';

  const mechanicalFabricator = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  mechanicalFabricator(
    'minecraft:iron_ingot',
    'crusty_chunks:advanced_alloy_component',
    'advanced_alloy_component_from_iron_ingot'
  );

  mechanicalFabricator(
    'crusty_chunks:advanced_alloy_ingot',
    'crusty_chunks:advanced_alloy_component',
    'advanced_alloy_component_from_advanced_alloy_ingot'
  );

  mechanicalFabricator(
    'crusty_chunks:aluminum_block',
    'crusty_chunks:precision_component',
    'precision_component_from_aluminum_block'
  );

  mechanicalFabricator(
    'crusty_chunks:steel_ingot',
    'crusty_chunks:steel_component',
    'steel_component_from_steel_ingot'
  );

  mechanicalFabricator(
    'minecraft:copper_block',
    'crusty_chunks:copper_component',
    'copper_component_from_copper_block'
  );
});
