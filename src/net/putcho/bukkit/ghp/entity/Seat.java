package net.putcho.bukkit.ghp.entity;

import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.World;

public class Seat extends EntityArmorStand{
	private Gondora main;

	private int dethcount;

	public Seat(World world){
		super(world);
		dethcount = 0;
		this.setInvisible(true);
	}

	public void setMain(Gondora main){
		this.main = main;
	}

	@Override
	public void t_(){
		super.t_();
		if(main != null){
			if(main.dead){
				this.die();
				return;
			}
			if(this.passenger == null){
				if(dethcount > 10){
					this.die();
					return;
				}
				dethcount++;
			}else{
				dethcount = 0;
			}
		}else{
			this.die();
		}
	}
}
