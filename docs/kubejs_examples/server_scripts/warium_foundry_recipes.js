// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Foundry
// Usage: Foundry takes a template + material -> result
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:foundry';
  const ID_PREFIX = 'kubejs:warium/foundry/';

  const foundry = (template, material, result, idSuffix) => {
    event
      .custom({
        type: TYPE,
        template,
        material,
        result
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  foundry(
    'crusty_chunks:component_foundry_template',
    'immersiveengineering:ingot_steel',
    { item: 'crusty_chunks:cast_component' },
    'cast_component_from_steel_ingot'
  );

  foundry(
    'crusty_chunks:cylinder_foundry_template',
    'crusty_chunks:brass_ingot',
    { item: 'crusty_chunks:steel_cylinder' },
    'steel_cylinder_from_brass_ingot'
  );

  // `count` is optional; include it when the recipe outputs multiples.
  foundry(
    'crusty_chunks:small_projectile_foundry_template',
    'immersiveengineering:nugget_lead',
    { item: 'crusty_chunks:small_projectile', count: 3 },
    'small_projectile_x3_from_lead_nugget'
  );
});
