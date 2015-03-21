package us.corenetwork.moblimiter;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EntityEquipment;

public class OldMobKiller implements Listener {
	private static HashSet<String> affectedMobs = new HashSet<String>();
	private static int killTreshold;
	private static int nearMobsCount;
	private static int nearMobsSearchRange;
    private static boolean killAggravatedMobs;
    private static boolean killNonAggravatedMobs;
    private static boolean killPickedItemsMobs;
	
	public static void init()
	{
		loadConfig();
		
		Bukkit.getPluginManager().registerEvents(new OldMobKiller(), MobLimiter.instance);

		Bukkit.getScheduler().runTaskTimer(MobLimiter.instance, new MobKillerTimer(), 20, Settings.getInt(Setting.OLD_MOB_KILLER_INTERVAL));
	}
	
	public static void loadConfig()
	{
		for (String mobName : (List<String>) Settings.getList(Setting.OLD_MOB_KILLER_AFFECTED_MOBS))
		{
			affectedMobs.add(mobName.toLowerCase());
		}
		
		killTreshold = Settings.getInt(Setting.OLD_MOB_KILLER_TRESHOLD);
		nearMobsSearchRange = Settings.getInt(Setting.OLD_MOB_KILLER_NEAR_MOBS_SEARCH_RANGE);
		nearMobsCount = Settings.getInt(Setting.OLD_MOB_KILLER_NEAR_MOBS_COUNT);
        killAggravatedMobs = Settings.getBoolean(Setting.OLD_MOB_KILLER_CULL_AGGRAVATED);
        killNonAggravatedMobs = Settings.getBoolean(Setting.OLD_MOB_KILLER_CULL_NONAGGRAVATED);
        killPickedItemsMobs = Settings.getBoolean(Setting.OLD_MOB_KILLER_CULL_PICKED_ITEMS);
	}
	
	private static int getNumberOfNearbyMobs(Entity mob)
	{
		int count = 0;
		for (Entity e : mob.getNearbyEntities(nearMobsSearchRange, nearMobsSearchRange, nearMobsSearchRange))
		{
			EntityType type = e.getType();
			if (type.getName() != null && affectedMobs.contains(type.getName().toLowerCase()))
				count++;
		}
		
		return count;
	}
	
	private static boolean hasStolenArmor(LivingEntity mob)
	{
		EntityEquipment equipment = mob.getEquipment();
		return equipment.getBootsDropChance() >= 1 || equipment.getChestplateDropChance() >= 1 || equipment.getHelmetDropChance() >= 1 || 
			   equipment.getItemInHandDropChance() >= 1 || equipment.getLeggingsDropChance() >= 1;
	}
	
	private static boolean hasPlayerTarget(Creature mob)
	{
		return mob.getTarget() != null && mob.getTarget() instanceof Player;
	}
	
	private static class MobKillerTimer implements Runnable
	{
		@Override
		public void run() {	
			for (World world : Bukkit.getWorlds())
			{
				for (Entity mob : world.getEntities())
				{
					if (!(mob instanceof Creature))
						continue;
									
					Creature creature = (Creature) mob;
					
					EntityType type = mob.getType();
					if (!affectedMobs.contains(type.getName().toLowerCase()))
						continue;
										
					int age = mob.getTicksLived();
														
					if (age > killTreshold && getNumberOfNearbyMobs(mob) > nearMobsCount)
					{
                        boolean aggravated = hasPlayerTarget(creature);
						if (((aggravated && killAggravatedMobs) || (!aggravated && killNonAggravatedMobs)) && (killPickedItemsMobs || !hasPlayerTarget(creature)))
						{
							mob.remove();
						}
					}
					
				}
			}
		}
	}
	
}
