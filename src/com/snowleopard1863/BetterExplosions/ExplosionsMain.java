package com.snowleopard1863.BetterExplosions;

import java.util.logging.Logger;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class ExplosionsMain extends JavaPlugin implements Listener {
	private  PluginDescriptionFile pdfile = getDescription();
	private Logger logger = Logger.getLogger("Minecraft");
	@Override
	public void onEnable() {
		logger.info(pdfile.getName() + " v" + pdfile.getVersion() + " has been enbaled.");
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onLoad() {
		super.onLoad();
		logger = getLogger();
	}
	@Override
	public void onDisable(){
		logger.info(pdfile.getName() + " v" + pdfile.getVersion() + " has been disabled");
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onExplode(EntityExplodeEvent event){
		for (Block b : event.blockList()) {
			//float rand = (float) 0 + (float) (Math.random() * ((100 - 0) + 1));
			if (b.getType() == Material.TNT || b.getType() == Material.FIRE){
				break;
			}
			float x = (float) 0.25 + (float) (Math.random() * ((0.5 - 0.25) + 0.25));
			if (Math.random() < 0.5) {
				x = x * -1;
			}
			float y = (float) 0.25 + (float) (Math.random() * ((1 - 0.25) + 0.25));
			if (Math.random() < 0.5) {
				y = y * -1;
			}
			float z = (float) 0.25 + (float) (Math.random() * ((0.5 - 0.25) + 0.25));
			if (Math.random() < 0.5) {
				z = z * -1;
			}
			FallingBlock fallingblock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
			fallingblock.setDropItem(true);
			fallingblock.setVelocity(new Vector(x, y, z));
			b.setType(Material.AIR);
			b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getTypeId());
		}
	}
}
