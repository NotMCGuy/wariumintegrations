// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Assembly Machine - Bore Head (Lathe)
// Usage: Bore/Lathe: hollows barrels/projectiles and bores cast parts
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:assembly_bore';
  const ID_PREFIX = 'kubejs:warium/assembly_bore/';

  const bore = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  bore('crusty_chunks:bent_component', 'crusty_chunks:component_bore_result', 'component_bore_result_from_bent_component');
  bore('crusty_chunks:small_projectile', 'crusty_chunks:small_projectile_bored', 'small_projectile_bored_from_small_projectile');
  bore('crusty_chunks:cannon_barrel', 'crusty_chunks:cannon_barrel_bored', 'cannon_barrel_bored_from_cannon_barrel');
});
