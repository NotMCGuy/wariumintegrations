// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Centrifuge
// Usage: Separates dusts into tiny dusts (pyrochlore, bauxite, uranium, lithium)
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:centrifuge';
  const ID_PREFIX = 'kubejs:warium/centrifuge/';

  const centrifuge = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  centrifuge(
    'crusty_chunks:filtered_pyrochlore_dust',
    'crusty_chunks:niobium_tiny_dust',
    'niobium_tiny_dust_from_filtered_pyrochlore_dust'
  );

  centrifuge(
    'crusty_chunks:aluminate_dust',
    'crusty_chunks:aluminum_tiny_dust',
    'aluminum_tiny_dust_from_aluminate_dust'
  );

  centrifuge(
    'crusty_chunks:uranium_neutral_dust',
    'crusty_chunks:uranium_enriched_tiny_dust',
    'uranium_enriched_tiny_dust_from_uranium_neutral_dust'
  );

  centrifuge(
    'crusty_chunks:lithium_dust',
    'crusty_chunks:tiny_lithium_deuteride',
    'tiny_lithium_deuteride_from_lithium_dust'
  );
});
