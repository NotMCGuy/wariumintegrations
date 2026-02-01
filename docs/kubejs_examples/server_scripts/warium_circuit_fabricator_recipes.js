// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Circuit Fabricator
// Usage: Fabricates circuits from unfabricated components
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:circuit_fabricator';
  const ID_PREFIX = 'kubejs:warium/circuit_fabricator/';

  const circuitFabricator = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  circuitFabricator(
    'crusty_chunks:unfabricated_tech_component',
    'crusty_chunks:tech_component',
    'tech_component_from_unfabricated_tech_component'
  );

  circuitFabricator(
    'crusty_chunks:unfabricated_advanced_component',
    'crusty_chunks:advanced_component',
    'advanced_component_from_unfabricated_advanced_component'
  );

  circuitFabricator(
    'crusty_chunks:unfabricated_circuit_board',
    'crusty_chunks:circuit_board',
    'circuit_board_from_unfabricated_circuit_board'
  );
});
