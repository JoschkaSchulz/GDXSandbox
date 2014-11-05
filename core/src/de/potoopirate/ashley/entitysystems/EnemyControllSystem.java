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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.ashley.component.EnemyComponent;
import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.entity.PlayerEntity;

public class EnemyControllSystem extends IteratingSystem {
	private ImmutableArray<Entity> entries;
	private PositionComponent position;
	private PositionComponent playerPosition;
	private MovementComponent movement;
	private Vector2 playerVector;
	private Vector2 enemyVector;
	private float speed;
	private float rotate;
	
	private Engine engine;
	private ImmutableArray<Entity> players;
	
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<MovementComponent> movementMapper;

	public EnemyControllSystem() {
		super(Family.getFor(EnemyComponent.class, PositionComponent.class, MovementComponent.class));
		
		this.speed = 0;
		this.rotate = 0;
		this.rotationDirection = 0;
		this.playerVector = new Vector2();
		this.enemyVector = new Vector2();
		
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		movementMapper = ComponentMapper.getFor(MovementComponent.class);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
		players = engine.getEntitiesFor(Family.getFor(PlayerComponent.class));
		
		super.addedToEngine(engine);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
	private short rotationDirection;
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		position = positionMapper.get(entity);
		movement = movementMapper.get(entity);
		
		players = engine.getEntitiesFor(Family.getFor(PlayerComponent.class));
		playerPosition = positionMapper.get(players.first());
		playerVector = new Vector2(playerPosition.x,playerPosition.y);
		enemyVector = new Vector2(position.x, position.y);
		
		Vector2 dir = new Vector2(playerVector).sub(enemyVector).nor();
		float ang = dir.angle();
		
		float calc = ang - position.direction.angle();
		
		position.direction = dir;
		position.rotation = position.direction.angle();
		
		
	}
}
