# Stackable Shulker Automation

NeoForge 1.21.1 server-compatible mod that makes identical vanilla shulker boxes stack up to 64 everywhere by patching the shulker box item default `minecraft:max_stack_size` component.

## Behavior

- Same shulker color + same components + same contents can stack up to 64.
- Different color does not stack.
- Different contents do not stack.
- Different custom name, lock data, loot table data, or other meaningful components do not stack.
- Hoppers, crafters, Pretty Pipes, vanilla inventories, and dropped item merging use the same server-side max-stack-size rule.
- The old Stackable Shulker Boxes datapack is no longer required.

## How this version works

Version 1.0.1 uses NeoForge's `ModifyDefaultComponentsEvent` to patch all vanilla shulker box item defaults to `DataComponents.MAX_STACK_SIZE = 64`. This is more reliable than trying to override `ItemStack#getMaxStackSize` with a mixin.

On server startup, the log should include a line like:

```text
Stackable Shulker Automation patched 17 shulker box item(s) to max stack size 64.
```

## Installation

1. Build the jar with GitHub Actions.
2. Put the built jar into the server `mods` folder.
3. Remove the old Stackable Shulker Boxes datapack.
4. Restart the server.

The mod is safe to install on clients too, but it is designed so the server can run it without requiring every player to install it. Client installation may make manual inventory interactions feel more visually consistent, but server automation behavior is handled server-side.

## Testing checklist

1. Confirm the server log says it patched 17 shulker box item(s).
2. Craft or give two shulker boxes of the same color.
3. Put identical contents inside both, or test two empty shulkers first.
4. Confirm they stack manually.
5. Put them through a hopper/crafter into a chest and confirm the chest merges them.
6. Put them through Pretty Pipes and confirm the target inventory merges them.
7. Confirm different colors do not stack.
8. Confirm same color but different contents do not stack.
