// ============================================================================
// Warium Integrations - KubeJS Recipe Examples
// Machine: Assembly Machine - Shear Head
// Usage: Shear: cuts plates/components into wires, gears, and trims
// ============================================================================

ServerEvents.recipes(event => {
  const TYPE = 'warium:assembly_shear';
  const ID_PREFIX = 'kubejs:warium/assembly_shear/';

  const shear = (input, resultItem, idSuffix) => {
    event
      .custom({
        type: TYPE,
        input,
        result: { item: resultItem }
      })
      .id(`${ID_PREFIX}${idSuffix}`);
  };

  // Examples
  shear('crusty_chunks:cast_component', 'crusty_chunks:steel_gear', 'steel_gear_from_cast_component');
  shear('crusty_chunks:bent_component', 'crusty_chunks:cut_component', 'cut_component_from_bent_component');
  shear('crusty_chunks:copper_plate', 'crusty_chunks:copper_wire', 'copper_wire_from_copper_plate');
  shear('crusty_chunks:steelplate', 'crusty_chunks:steel_wire', 'steel_wire_from_steel_plate');
});
