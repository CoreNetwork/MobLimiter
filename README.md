MobLimiter 3
============

## Download

Compile (a Maven project) or download from our [build service](http://build.core-network.us:8080/job/MobLimiter/).


## Configuration

 * **Note on bukkit settings** it is recommended to set the ticks-per.monster-spawns value in the bukkit.yml to value of 10 or higher (im using 80)
 * **Note on spawners** To allow spawners to reach the full spawnerlimit AND allow normal viewdistance limits to also be reached at the same time, it is recommended to set the spawn-limits.monsters value to a value of more than both of them combined (i have spawner limit of 100, viewdistance limits of 40 monsters so i have mine set to monsters limit to 200.) This way spawner usage won't dry up normal spawn activity.

There are three levels of configuration
global level, group level, and individual mob level, almost all options are optional at the individual mob level and they will inherit the setting from their group if unspecified.

Keyword descriptions:
 * Name: You can specify a name to give a plural form version of any group or mob. If not given, groups default to the group name, mobs without a name will not be listed in counting commands.
 * ChuckLimit: The number of mobs allowed to spawn in a chunk. (group and individual entity level)
 * ViewDistanceLimit: The number of mobs allowed to spawn in a the full view distance. (group and individual entity level)
 * WarnOverLimit: Wether or not the TooManyMobs should be shown for the group (group level only)
 * SpawnerLimit: A seperate limit for spawner mobs. This can allow spawner mobs to go beyond the normal limits. (individual entity level only)
 * SpawnDistanceMax: A maximum distance away from a player that a mob is allowed to spawn. (group and individual entity level)
 * SpawnDistanceMin: A minimum distance from a player a mob is allowed to spawn. (group and individual entity level)
 * SpawnHeightMax: A maximum height difference from a player a mob is allowed to spawn. (group and individual entity level)
 * ViewDistanceChunks: The radius of chunks around a player that is used as the view distance (should probably be set to match your server.properties)
 * EnabledWorlds: A list of worlds that are limited by this mod
 * Debug: Print debug information
 * NoHorseBreed: Disable all horse breeding
 * NoPigmenPortal: Disable pigmen from coming out of portals (works on all worlds)
 * BreedingSpamDelaySeconds: a delay on the error message given to players when they try to breed animals while over a limit.

## Recommended/Example Config

It is recommended to copy this base config over to your config.yml (as much of it won't be auto populated)

``` yaml
Groups:
  Animals:
    ChunkLimit: 40
    ViewDistanceLimit: 200
    WarnOverLimit: true
    HORSE:
      Name: Horses
    COW:
      Name: Cows
    SHEEP:
      Name: Sheep
    CHICKEN:
      Name: Chickens
    PIG:
      Name: Pigs
    WOLF:
      Name: Wolves
    OCELOT:
      Name: Cats
  Villagers:
    Name: Villagers and Golems
    ChunkLimit: 40
    ViewDistanceLimit: 120
    WarnOverLimit: true
    IRON_GOLEM:
      Name: Golems
      ChunkLimit: 4
      ViewDistanceLimit: 40
    VILLAGER:
      Name: Villagers
      ViewDistanceLimit: 80
    SNOWMAN:
      Name: Snowmen
      ChunkLimit: 4
      ViewDistanceLimit: 40
  Monsters:
    ChunkLimit: 10
    ViewDistanceLimit: 40
    SpawnDistanceMax: 80
    SpawnHeightMax: 30
    BLAZE:
      Name: Blazes
      SpawnerLimit: 100
      SpawnDistanceMin: 5
    CAVE_SPIDER:
      Name: Spiders
      SpawnerLimit: 100
    CREEPER:
      Name: Creepers
    ENDERMAN:
      Name: Endermen
      ChunkLimit: 100
    PIG_ZOMBIE:
      Name: Pigmen
    SKELETON:
      Name: Skeletons
      SpawnerLimit: 100
    SLIME:
      Name: Slimes
    SPIDER:
      Name: Spiders
    ZOMBIE:
      Name: Zombies
      SpawnerLimit: 50
    WITCH:
      Name: Witches
    GHAST:
      Name: Ghasts
      SpawnHeightMax: 50
      ViewDistanceLimit: 6
    MAGMA_CUBE:
      Name: Magma Cubes
      ViewDistanceLimit: 10
    SILVERFISH:
      ViewDistanceLimit: 10
  Neutrals:
    ChunkLimit: 1
    ViewDistanceLimit: 10
    SQUID:
      Name: Squid
    BAT:
      Name: Bats
ViewDistanceChunks: 8
EnabledWorlds:
- world
- world_nether
Debug: false
Messages:
  NoPermission: '&cNo permission!'
  MobCountLine: '&6<MobName>: <ChunkCount>&7/<ChunkLimit> in chunk, &6<ViewDistanceCount>&7/<ViewDistanceLimit>
    in view distance.'
  TooManyMobs: '&cPlease consider killing some or moving nearby mobs to keep the server
    healthy.'
  BreedLimitReached: '&cYou cannot breed more than <MobLimit> <MobName> in view distance.'
  NoHorseBreeding: '&cSorry, you are not allowed to breed horses. Find another one
    in the wild.'
NoHorseBreed: false
NoPigmenPortal: true
BreedingSpamDelaySeconds: 5
Grid:
  None:
    Id: 20
    Data: 0
  Low:
    Id: 35
    Data: 5
  Medium:
    Id: 35
    Data: 4
  High:
    Id: 35
    Data: 1
  Exceed:
    Id: 35
    Data: 14
  Duration: 600
```

## Commands

There is a command and permission node for each group you define, you can change the config to group your mobs however you want, in my example config above the commands are:

```
/animals
/villagers
/monsters
```

Each command counts the mobs you define as being in that group in your config

All commands have the same arguments, the `/animals` command will be used as an example here,
but every argument will work the same way for the `villagers` command as well.

    /animals show

Will display a grid 10 blocks above the player, like depict in this image:

![Grid visualization of a count command](https://f.cloud.github.com/assets/616791/1810640/e3effa1a-6e2d-11e3-8563-1bc4077fd4d9.png)

The grid is aligned by chunk and there are 5 different layouts/colors that are drawn per chunk:

 * **Edges / transparent**: No creatures apply to counted group
 * **One bar in the middle / lime green**: Max creature count is below 80% of limit
 * **Two bars in the middle / yellow**: Max creature count is above 80% of limit
 * **Dotted border / orange**: Max creature count is above 90% of limit
 * **Solid border / red**: Max creature count exceeds limit

With default arguments, the grid will disappear after 30 seconds (this can be configured with the field `Grid.Duration`).

To make the grid stay, add the `keep` argument to the command, like so:

    /animals show keep

This will make the grid stay until you reload the chunks or type in this command:

    /animals hide

## Permissions

There is a permission node for each group you define just like with the commands. In the example config the nodes are:

```
moblimiter.command.animals
moblimiter.command.villagers
moblimiter.command.monsters
```
