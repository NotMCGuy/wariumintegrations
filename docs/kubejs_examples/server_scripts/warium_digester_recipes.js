// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Digester (Chemical Digester)
// Usage: Processes dusts into filtered/processed forms
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:digester';
  const ID_PREFIX = 'kubejs:warium/digester/';

  const digester = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  digester(
    'crusty_chunks:pyrochlore_dust',
    'crusty_chunks:filtered_pyrochlore_dust',
    'filtered_pyrochlore_dust_from_pyrochlore_dust'
  );

  digester(
    'crusty_chunks:bauxite_dust',
    'crusty_chunks:aluminate_dust',
    'aluminate_dust_from_bauxite_dust'
  );

  digester(
    'crusty_chunks:iron_ore_dust',
    'crusty_chunks:iron_concentrate',
    'iron_concentrate_from_iron_ore_dust'
  );

  digester(
    'crusty_chunks:copper_ore_dust',
    'crusty_chunks:copper_concentrate',
    'copper_concentrate_from_copper_ore_dust'
  );
});
