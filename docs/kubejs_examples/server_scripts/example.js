// priority: 0
// Visit the wiki for more info - https://kubejs.com/
// This file is the "yes it's alive" heartbeat. If you delete it, the logs get lonely.

console.info('Hello, World! (Loaded server scripts)');

// Example custom recipe using this mod's registered recipe type.
// NOTE: The type is NOT `warium:...` — this mod's namespace is `warium_integrations`.
ServerEvents.recipes(event => {

  const TYPE = 'warium_integrations:mechanical_fabricator';

  event
    .custom({
      type: TYPE,
      input: 'minecraft:iron_ingot',
      result: { item: 'crusty_chunks:advanced_alloy_component' }
    })
    .id('kubejs:warium_integrations/mechanical_fabricator/advanced_alloy_component_from_iron_ingot');
});
