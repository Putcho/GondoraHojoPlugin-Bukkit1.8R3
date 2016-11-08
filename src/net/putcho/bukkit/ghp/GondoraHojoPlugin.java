package net.putcho.bukkit.ghp;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftBoat;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import net.putcho.bukkit.ghp.entity.Gondora;
import net.putcho.bukkit.ghp.events.GHPListener;

public class GondoraHojoPlugin extends JavaPlugin{
	public static JavaPlugin plugin;

	public static double gy = 1.5;
	public static double gr = 2.3;

	@Override
	public void onEnable(){
		plugin = this;

		getServer().getPluginManager().registerEvents(new GHPListener(), this);
	}

	@Override
	public void onDisable(){
		for(World w: this.getServer().getWorlds()){
			for(Entity e: w.getEntities()){
				if(e instanceof CraftBoat){
					if(((CraftBoat)e).getHandle() instanceof Gondora){
						((CraftBoat)e).getHandle().die();
					}
				}
			}
		}
	}

}
