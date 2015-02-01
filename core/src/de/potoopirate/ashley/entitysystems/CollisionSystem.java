package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.physics.bullet.collision.CollisionObjectWrapper;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.ashley.AssetHandler;
import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;
import de.potoopirate.ashley.entity.EnemyEntity;
import de.potoopirate.ashley.entity.ExplosionEntity;
import de.potoopirate.ashley.entity.FieldEntity;
import de.potoopirate.ashley.entity.PlayerEntity;

public class CollisionSystem extends EntitySystem {
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<CollisionComponent> collisionMapper;
	private ComponentMapper<TextureComponent> textureMapper;
	private ComponentMapper<MovementComponent> movementMapper;
	private ComponentMapper<PlayerComponent> playerMapper;
	
	private ImmutableArray<Entity> entities;
	private Array<Entity> newShips;
	private Array<Entity> removeShips;
	private Circle e1Circle;
	private Circle e2Circle;
	private TextureRegion e1Texture;
	private TextureRegion e2Texture;
	private PositionComponent e1Position;
	private PositionComponent e2Position;
	private CollisionComponent e1Collision;
	private CollisionComponent e2Collision;
	private MovementComponent e1Movement;
	private MovementComponent e2Movement;
	
	private Engine engine;
	private PlayerComponent playerComponent;
	
	public CollisionSystem() {
		
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
		textureMapper = ComponentMapper.getFor(TextureComponent.class);
		movementMapper = ComponentMapper.getFor(MovementComponent.class);
		playerMapper = ComponentMapper.getFor(PlayerComponent.class);
		
		newShips = new Array<Entity>();
		removeShips = new Array<Entity>();
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
		
		entities = engine.getEntitiesFor(Family.getFor(PositionComponent.class, CollisionComponent.class, TextureComponent.class, MovementComponent.class));
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		for(int e1 = 0; e1 < entities.size(); e1++) {
			for(int e2 = 0; e2 < entities.size(); e2++) {
				if(e1 != e2) {
					e1Position = positionMapper.get(entities.get(e1));
					e2Position = positionMapper.get(entities.get(e2));
					e1Collision = collisionMapper.get(entities.get(e1));
					e2Collision = collisionMapper.get(entities.get(e2));
					e1Texture = textureMapper.get(entities.get(e1)).textureRegion;
					e2Texture = textureMapper.get(entities.get(e2)).textureRegion;
					e1Movement = movementMapper.get(entities.get(e1));
					e2Movement = movementMapper.get(entities.get(e2));
					e1Circle = new Circle(e1Position.x+e1Texture.getRegionWidth()/2, e1Position.y+e1Texture.getRegionHeight()/2, e1Collision.circleRadius);
					e2Circle = new Circle(e2Position.x+e2Texture.getRegionWidth()/2, e2Position.y+e2Texture.getRegionHeight()/2, e2Collision.circleRadius);
					
					if(Intersector.overlaps(e1Circle, e2Circle)) {
						if(!e1Collision.isCrashed || !e2Collision.isCrashed) {
							int speed = (int)(e1Movement.speed > e2Movement.speed ? e1Movement.speed+10 : e2Movement.speed+10);
							newShips.add(new EnemyEntity((int)e1Position.x, (int)e1Position.y, speed));
							engine.addEntity(new ExplosionEntity(e1Position.x-(AssetHandler.EXPLOSION.getWidth()/2), e1Position.y-(AssetHandler.EXPLOSION.getHeight()/2)));
						}
						removeShips.add(entities.get(e1));
						removeShips.add(entities.get(e2));
						e1Collision.isCrashed = true;
						e2Collision.isCrashed = true;
					}
				}
			}	
		}
		
		for(Entity e : removeShips) {
			if(e instanceof EnemyEntity) {
				engine.removeEntity(e);
			} else if (e instanceof PlayerEntity) {
				playerComponent = playerMapper.get(e);
				playerComponent.currentState = playerComponent.STATE_GAME_OVER;
			}
		}
		
		for(Entity e : newShips) {
			engine.addEntity(e);
		}
		
		newShips.clear();
		removeShips.clear();
	}
	
	
}
