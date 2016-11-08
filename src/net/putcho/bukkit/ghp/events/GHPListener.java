package net.putcho.bukkit.ghp.events;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftBoat;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import net.putcho.bukkit.ghp.GondoraHojoPlugin;
import net.putcho.bukkit.ghp.entity.Gondora;
import net.putcho.bukkit.ghp.scheduler.MountTask;

public class GHPListener implements Listener{
	@EventHandler
	public void onSeatClick(PlayerInteractEntityEvent e){
		Entity en = e.getRightClicked();
		if(en instanceof CraftBoat){
			e.setCancelled(true);
			new MountTask(en, e.getPlayer()).runTaskLater(GondoraHojoPlugin.plugin, 2);
		}
	}

	@EventHandler
	public void onBoatMove(VehicleMoveEvent e){
		if(e.getVehicle() instanceof CraftBoat){
			if(((CraftBoat)e.getVehicle()).getHandle() instanceof Gondora){
				Gondora a = (Gondora) ((CraftBoat)e.getVehicle()).getHandle();

				if(a.getSeat() != null){
					double d0 = -Math.cos(a.yaw * 3.141592653589793D / 180.0D) * GondoraHojoPlugin.gr;
					double d1 = -Math.sin(a.yaw * 3.141592653589793D / 180.0D) * GondoraHojoPlugin.gr;
					a.getSeat().setPositionRotation(a.locX + d0, a.locY - GondoraHojoPlugin.gy, a.locZ + d1, a.yaw, a.pitch);
					a.getSeat().motX = a.motX;
					a.getSeat().motY = a.motY;
					a.getSeat().motZ = a.motZ;
				}
			}
		}
	}
}
