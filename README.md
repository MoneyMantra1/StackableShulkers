# Stackable Shulker Automation

NeoForge 1.21.1 server-compatible mod that makes shulker boxes stack up to 64 everywhere while still requiring identical item data.

## Behavior

- Same shulker color + same components + same contents can stack up to 64.
- Different color does not stack.
- Different contents do not stack.
- Different custom name, lock data, loot table data, or other meaningful components do not stack.
- Hoppers, crafters, Pretty Pipes, vanilla inventories, and dropped item merging use the same rule because the core ItemStack stack limit/equality behavior is patched.
- The old `minecraft:max_stack_size` datapack component is ignored for shulker comparison only, so shulkers previously touched by the datapack can merge with fresh shulkers when every meaningful property matches.

## Installation

1. Build the jar with GitHub Actions.
2. Put the built jar into the server `mods` folder.
3. Remove the old Stackable Shulker Boxes datapack.
4. Restart the server.

The mod is safe to install on clients too, but it is designed so the server can run it without requiring players to install it.

## Testing checklist

1. Craft or give two shulker boxes of the same color.
2. Put identical contents inside both.
3. Confirm they stack manually.
4. Put them through a hopper into a chest and confirm the chest merges them.
5. Put them through Pretty Pipes and confirm the target inventory merges them.
6. Confirm different colors do not stack.
7. Confirm same color but different contents do not stack.
8. Confirm shulkers previously created by the datapack can stack with newly created shulkers when their real data matches.
