package net.putcho.bukkit.ghp.scheduler;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftBoat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.World;
import net.putcho.bukkit.ghp.GondoraHojoPlugin;
import net.putcho.bukkit.ghp.entity.Gondora;
import net.putcho.bukkit.ghp.entity.Seat;

public class MountTask extends BukkitRunnable{
	private Entity target;
	private Player p;

	public MountTask(Entity target, Player p){
		this.target = target;
		this.p = p;
	}

	@Override
	public void run() {
		if(((CraftBoat)target).getHandle() instanceof Gondora){
			World w = ((CraftWorld)target.getWorld()).getHandle();
			Gondora b = (Gondora)((CraftBoat)target).getHandle();
			if(b.passenger == null){
				((CraftPlayer)p).getHandle().mount(b);
			}else{
				if(b.getSeat() == null || b.getSeat().dead){
					Seat as = new Seat(w);
					as.spawnIn(w);
					b.setSeat(as);
					as.setMain(b);
					double d0 = -Math.cos(b.yaw * 3.141592653589793D / 180.0D) * GondoraHojoPlugin.gr;
					double d1 = -Math.sin(b.yaw * 3.141592653589793D / 180.0D) * GondoraHojoPlugin.gr;
					as.setPositionRotation(b.locX + d0, b.locY - GondoraHojoPlugin.gy, b.locZ + d1, b.yaw + 90, b.pitch);
					as.motX = b.motX;
					as.motY = b.motY;
					as.motZ = b.motZ;
					w.addEntity(as, SpawnReason.CUSTOM);
					new MountSeat(as, ((CraftPlayer)p).getHandle()).runTaskLater(GondoraHojoPlugin.plugin, 2);
				}else if(b.getSeat().passenger == null){
					((CraftPlayer)p).getHandle().mount(b.getSeat());
				}
			}
		}else{
			double x = target.getLocation().getX();
			double y = target.getLocation().getY();
			double z = target.getLocation().getZ();
			float yaw = target.getLocation().getYaw();
			float pitch = target.getLocation().getPitch();
			double maxspeed = ((CraftBoat)target).getMaxSpeed();
			((CraftBoat)target).getHandle().die();
			World w = ((CraftWorld)target.getWorld()).getHandle();
			Gondora boat = new Gondora(w);
			boat.spawnIn(w);
			boat.setPositionRotation(x, y, z, yaw, pitch);
			boat.maxSpeed = maxspeed;
			w.addEntity(boat, SpawnReason.CUSTOM);
			((CraftPlayer)p).getHandle().mount(boat);
		}
	}

	private class MountSeat extends BukkitRunnable{
		private Seat seat;
		private EntityPlayer p;

		MountSeat(Seat seat, EntityPlayer p){
			this.seat = seat;
			this.p = p;
		}

		@Override
		public void run(){
			p.mount(seat);
		}
	}
}