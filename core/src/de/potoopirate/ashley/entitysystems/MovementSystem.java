package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;

public class MovementSystem extends IteratingSystem {
	
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<MovementComponent> mm;
	private PositionComponent position;
	private MovementComponent movement;
	
	public MovementSystem() {
		super(Family.getFor(PositionComponent.class, MovementComponent.class));
		
		pm = ComponentMapper.getFor(PositionComponent.class);
		mm = ComponentMapper.getFor(MovementComponent.class);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		position = pm.get(entity);
		movement = mm.get(entity);
		
		position.x += deltaTime * movement.speed * position.direction.x;
		position.y += deltaTime * movement.speed * position.direction.y;
	}
	
}
