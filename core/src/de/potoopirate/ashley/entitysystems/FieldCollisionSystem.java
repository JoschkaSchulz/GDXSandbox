package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.FieldComponent;
import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;

public class FieldCollisionSystem extends EntitySystem {

	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<CollisionComponent> collisionMapper;
	private ComponentMapper<PlayerComponent> playerMapper;
	private ComponentMapper<TextureComponent> textureMapper;
	
	private Engine engine;
	private ImmutableArray<Entity> players;
	private ImmutableArray<Entity> fields;
	private PositionComponent fieldPosition;
	private PositionComponent playerPosition;
	private CollisionComponent fieldCollision;
	private CollisionComponent playerCollision;
	private TextureComponent playerTexture;
	private PlayerComponent player;
	
	private float rotationX, rotationY;
	private float imageCenterX, imageCenterY;
	
	public FieldCollisionSystem() {
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
		playerMapper = ComponentMapper.getFor(PlayerComponent.class);
		textureMapper = ComponentMapper.getFor(TextureComponent.class);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		this.engine = engine;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		players = engine.getEntitiesFor(Family.getFor(MovementComponent.class));
		fields = engine.getEntitiesFor(Family.getFor(FieldComponent.class));
		
		for(int p = 0; p < players.size(); p++) {
			for(int f = 0; f < fields.size(); f++) {
				fieldCollision = collisionMapper.get(fields.get(f));
				fieldPosition = positionMapper.get(fields.get(f));
				playerCollision = collisionMapper.get(players.get(p));
				playerPosition = positionMapper.get(players.get(p));
				playerTexture = textureMapper.get(players.get(p));
				player = playerMapper.get(players.get(p));
				Circle c1 = new Circle(fieldPosition.x, fieldPosition.y, fieldCollision.circleRadius);
				imageCenterX = playerTexture.textureRegion.getRegionHeight();
				imageCenterY = playerTexture.textureRegion.getRegionHeight()/2;
				rotationX = (float) (Math.cos(Math.toRadians(playerPosition.rotation))*25);
				rotationY = (float) (Math.sin(Math.toRadians(playerPosition.rotation))*25);
				Circle c2 = new Circle(
						playerPosition.x+imageCenterX+rotationX, 
						playerPosition.y+imageCenterY+rotationY, 
						1);
				if(!Intersector.overlaps(c1, c2)) {
					playerPosition.direction.rotate(180).nor();
					playerPosition.rotation -= 180;
					//player.currentState = PlayerComponent.STATE_GAME_OVER;
				}
			}
		}
	}
	
}
