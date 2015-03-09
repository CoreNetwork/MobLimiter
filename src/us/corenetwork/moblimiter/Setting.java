package us.corenetwork.moblimiter;


import java.awt.List;
import java.util.Arrays;

import org.bukkit.DyeColor;
import org.bukkit.Material;

public enum Setting
{

	VIEW_DISTANCE_CHUNKS("ViewDistanceChunks", 10),
	NO_HORSE_BREED("NoHorseBreed", false),
	DEBUG_LOGGING("Debug", false),
	NO_PIGMEN_PORTAL("NoPigmenPortal", false),
	BREEDING_SPAM_DELAY_SECONDS("BreedingSpamDelaySeconds", 5),
	
	OLD_MOB_KILLER_INTERVAL("OldMobKiller.IntervalTicks", 6000),
	OLD_MOB_KILLER_TRESHOLD("OldMobKiller.KillingAgeTicks", 10000),
	OLD_MOB_KILLER_AFFECTED_MOBS("OldMobKiller.AffectedMobs", Arrays.asList(new String[] { "Zombie", "Skeleton", "Creeper", "Spider" })),
	OLD_MOB_KILLER_NEAR_MOBS_SEARCH_RANGE("OldMobKiller.NearMobSearchRangeBlocks", 4),
	OLD_MOB_KILLER_NEAR_MOBS_COUNT("OldMobKiller.NearMobKillCount", 4),
    OLD_MOB_KILLER_CULL_AGGRAVATED("OldMobKiller.CullAggravatedMobs", true),
    OLD_MOB_KILLER_CULL_NONAGGRAVATED("OldMobKiller.CullNonAggravatedMobs", true),
    OLD_MOB_KILLER_CULL_PICKED_ITEMS("OldMobKiller.CullPickedItemsMobs", true),


    ENABLED_WORLDS("EnabledWorlds", null),

	MESSAGE_NO_PERMISSION("Messages.NoPermission", "&cNo permission!"),
	MESSAGE_MOB_COUNT_LINE("Messages.MobCountLine", "&6<MobName>: <ChunkCount>&7/<ChunkLimit> in chunk, &6<ViewDistanceCount>&7/<ViewDistanceLimit> in view distance."),
	MESSAGE_MOB_COUNT_LINE_ONLY_CHUNK("Messages.MobCountLineOnlyChunk", "&6<MobName>: <ChunkCount>&7/<ChunkLimit> in chunk."),
	MESSAGE_MOB_COUNT_LINE_ONLY_VD("Messages.MobCountLineOnlyVD", "&6<MobName>: &6<ViewDistanceCount>&7/<ViewDistanceLimit> in view distance."),

	MESSAGE_TOO_MANY("Messages.TooManyMobs", "&cPlease consider killing some or moving nearby mobs to keep the server healthy."),
	MESSAGE_BREED_LIMIT_REACHED("Messages.BreedLimitReached", "&cYou cannot breed more than <MobLimit> <MobName> in view distance."),
	MESSAGE_NO_HORSE_BREEDING("Messages.NoHorseBreeding", "&cSorry, you are not allowed to breed horses. Find another one in the wild."),


	GRID_NONE_ID("Grid.None.Id", Material.GLASS.getId()),
	GRID_NONE_DATA("Grid.None.Data", 0),

	GRID_LOW_ID("Grid.Low.Id", Material.WOOL.getId()),
	GRID_LOW_DATA("Grid.Low.Data", (int) DyeColor.LIME.getWoolData()),

	GRID_MEDIUM_ID("Grid.Medium.Id", Material.WOOL.getId()),
	GRID_MEDIUM_DATA("Grid.Medium.Data", (int) DyeColor.YELLOW.getWoolData()),

	GRID_HIGH_ID("Grid.High.Id", Material.WOOL.getId()),
	GRID_HIGH_DATA("Grid.High.Data", (int) DyeColor.ORANGE.getWoolData()),

	GRID_EXCEED_ID("Grid.Exceed.Id", Material.WOOL.getId()),
	GRID_EXCEED_DATA("Grid.Exceed.Data", (int) DyeColor.RED.getWoolData()),

	GRID_DURATION("Grid.Duration", 30 * 20);

	private String name;
	private Object def;

	private Setting(String Name, Object Def)
	{
		name = Name;
		def = Def;
	}

	public String getString()
	{
		return name;
	}

	public Object getDefault()
	{
		return def;
	}
}
