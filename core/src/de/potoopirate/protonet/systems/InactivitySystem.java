package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.potoopirate.protonet.components.AvatarComponent;

public class InactivitySystem extends IteratingSystem {

	public final float DECREASE_TIME = 2f;
	
	private ComponentMapper<AvatarComponent> avatarMapper;
	private AvatarComponent avatar;
	private float counter;
	
	public InactivitySystem() {
		super(Family.getFor(AvatarComponent.class));
		
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
		
		counter = 0;
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		avatar = avatarMapper.get(entity);
		avatar.decreasteCounter += deltaTime;
		
		if(avatar.decreasteCounter > DECREASE_TIME && avatar.avatarImage.getScaleX() > 0.25) {
			avatar.avatarImage.scaleBy(-0.01f);
			avatar.decreasteCounter = 0;
		}
		
	}

}
