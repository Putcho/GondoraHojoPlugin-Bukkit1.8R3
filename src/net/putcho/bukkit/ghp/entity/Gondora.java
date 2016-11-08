package net.putcho.bukkit.ghp.entity;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityBoat;
import net.minecraft.server.v1_8_R3.World;
import net.putcho.bukkit.ghp.GondoraHojoPlugin;

public class Gondora extends EntityBoat{
	private Seat seat;

	public Gondora(World world){
		super(world);
	}

	public void setSeat(Seat e){
		this.seat = e;
	}

	@Override
	public void t_(){
		double mx = this.motX;
		double my = this.motY;
		double mz = this.motZ;
		super.t_();

		if(seat != null){
			if(!seat.dead){
				double d0 = -Math.cos(this.yaw * 3.141592653589793D / 180.0D) * GondoraHojoPlugin.gr;
				double d1 = -Math.sin(this.yaw * 3.141592653589793D / 180.0D) * GondoraHojoPlugin.gr;
				this.seat.setPositionRotation(this.locX + d0 + mx, this.locY + my - GondoraHojoPlugin.gy, this.locZ + d1 + mz, this.yaw + 90, this.pitch);
				this.seat.motX = this.motX;
				this.seat.motY = this.motY;
				this.seat.motZ = this.motZ;
			}
		}else{
			if(this.passenger == null){
				this.die();
				EntityBoat b = new EntityBoat(this.world);
				b.spawnIn(this.world);
				b.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, this.pitch);
				b.maxSpeed = this.maxSpeed;
				this.world.addEntity(b);
			}
		}
	}

	public Entity getSeat(){
		return seat;
	}

	@Override
	public AxisAlignedBB S() {
		return new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	}

	@Override
	public String toString(){
		return super.toString() + "[TestBoat]";
	}
}
