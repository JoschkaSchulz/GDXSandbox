package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;

public class DebugSystem extends IteratingSystem {
	private Array<Entity> renderQueue;
	private ShapeRenderer batch = new ShapeRenderer();
	
	private TextureRegion texture;
	private PositionComponent position;
	private CollisionComponent collision;
	private Vector2 direction;
	
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<TextureComponent> tm;
	private ComponentMapper<CollisionComponent> cm;
	
	public DebugSystem() {
		super(Family.getFor(PositionComponent.class, TextureComponent.class));
		
		pm = ComponentMapper.getFor(PositionComponent.class);
		tm = ComponentMapper.getFor(TextureComponent.class);
		cm = ComponentMapper.getFor(CollisionComponent.class);
	
		renderQueue = new Array<Entity>();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		batch.begin(ShapeType.Line);
		for(Entity entity : renderQueue) {
			texture = tm.get(entity).textureRegion;
			position = pm.get(entity);
			collision = cm.get(entity);
			direction = new Vector2(position.direction);
			direction.scl(1000f);
			direction.add(position.x, position.y);
			
			batch.setColor(Color.WHITE);
			batch.line(
					position.x+(texture.getRegionWidth()/2), 
					position.y+(texture.getRegionHeight()/2), 
					direction.x+(texture.getRegionWidth()/2),
					direction.y+(texture.getRegionHeight()/2));
			
			if (collision.isCrashed) {
				batch.setColor(1f, 0, 0, 1f);
			} else {
				batch.setColor(0, 1f, 0, 1f);
			}
			batch.circle(position.x+texture.getRegionWidth()/2, position.y+texture.getRegionHeight()/2, collision.circleRadius);
		}
		batch.end();
		renderQueue.clear();
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}

}
