package de.potoopirate.ashley.entitysystems;

import javax.crypto.spec.PSource;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.entity.PlayerEntity;

public class PlayerControllSystem extends IteratingSystem {
	public static final float MAX_SPEED = 100;
	public static final float MIN_SPEED = 15;
	
	private ImmutableArray<Entity> entries;
	private PlayerComponent player;
	private PositionComponent position;
	private MovementComponent movement;
	private float speed;
	private float rotate;
	private int x1;
	private int x2;
	
	private ComponentMapper<PlayerComponent> playerMapper;
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<MovementComponent> movementMapper;

	public PlayerControllSystem() {
		super(Family.getFor(PlayerComponent.class, PositionComponent.class, MovementComponent.class));
		
		this.speed = 0;
		this.rotate = 0;
		
		playerMapper = ComponentMapper.getFor(PlayerComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		movementMapper = ComponentMapper.getFor(MovementComponent.class);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		/*if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.getRoll() > 0) {
			speed = 1;
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.getRoll() < -120) {
			speed = -1;
		}*/
		
		
		if(Gdx.input.isTouched(0)) {
			x1 = Gdx.input.getX(0);
			if(x1 > Gdx.graphics.getWidth()/2) {
				rotate = 1;
			}
			if(x1 < Gdx.graphics.getWidth()/2) {
				rotate = -1;
			}
		} 
		
		if(Gdx.input.isTouched(1)) {
			x2 = Gdx.input.getX(1);
			if(x2 > Gdx.graphics.getWidth()/2) {
				rotate = 1;
			}
			if(x2 < Gdx.graphics.getWidth()/2) {
				rotate = -1;
			}
		}
		
		if(!Gdx.input.isTouched(0) && !Gdx.input.isTouched(1)) {
			rotate = 0;
		}
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			rotate = 1;
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			rotate = -1;
		}
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		player = playerMapper.get(entity);
		position = positionMapper.get(entity);
		movement = movementMapper.get(entity);
		
		movement.speed += speed;
		if (movement.speed > MAX_SPEED) movement.speed = MAX_SPEED;
		else if (movement.speed < MIN_SPEED) movement.speed = MIN_SPEED;
		position.rotation += rotate;
		position.direction.set(
				(float)Math.cos(Math.toRadians(position.rotation)), 
				(float)Math.sin(Math.toRadians(position.rotation)));
		speed = rotate = 0;
	}
}
