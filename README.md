# SimpleSpawnTP

> A Spigot plugin that sends players to spawn on join and allows them to go to spawn manually.

## Commands

- `/spawn` - Teleports you to spawn.

## Permissions

- `sstp.spawncmd` - Allows player to use the `/spawn` command. [default: true]
- `sstp.bypass` - Allows player to bypass automatically being teleported to spawn on join. [default: op]
- `sstp.*` - Grants all SimpleSpawnTP permissions.

## Configuration

The config file has a `worlds` section used to configure properites of reach world. Each key should be the name of the world (defaults: world, world_nether, world_the_end). Here's an example for a world named "world":

```yaml
worlds:
  world:
    yaw: 90.0
    use_personal_spawn: false
```

You can set the yaw that the player is facing when they go to spawn with the yaw property.<br>
Define each value in the yaws section with the key being the world name and the value being the yaw.<br>
Use F3 and look at the first number in the brackets in the "Facing" line to get the yaw.<br>
North = (+/-) 180.0, East = -90.0 / 270.0, South = 0.0, West = 90.0 / -270.0<br>
It can be a whole number or a decimal.<br>
Default value: 0.0

You can set whether the player will be teleported to their personal spawn or the world spawn with the use_personal_spawn property.<br>
Personal spawn is set with /spawnpoint, beds, and respawn anchors.<br>
If this is set to true, the player will be teleported to their personal spawn.<br>
If this is set to false, the player will be teleported to the world spawn (/setworldspawn).<br>
Default value: false
