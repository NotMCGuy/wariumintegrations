# Patchouli add-on guide

- Book id: `warium_integrations:integration_manual`.
- Ship your entries under `data/warium_integrations/patchouli_books/integration_manual/entries/` in your own mod jar. Namespacing the path to `warium_integrations` is fine; resource packs can target any namespace.
- Recommended category for contributed pages: set `"category": "warium_integrations:addons"` to appear in the Add-ons tab. You may also add new categories under `data/warium_integrations/patchouli_books/integration_manual/categories/` if you need more structure.
- Keep your JSON aligned with Patchouli's format (entry name, icon, sortnum, and `pages` list). The base entries in this repo are small examples.
- Patchouli is required for the manual; there is no fallback GUI. Add-ons should target the Patchouli book directly.
