package de.potoopirate.ashley.entitysystems;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;

public class TextureRenderSystem extends IteratingSystem {
	private Array<Entity> renderQueue;
	private SpriteBatch batch;
	private Comparator<Entity> comparator;
	
	private TextureRegion texture;
	private PositionComponent position;
	
	private ComponentMapper<PositionComponent> pm;
	private ComponentMapper<TextureComponent> tm;
	
	public TextureRenderSystem(SpriteBatch batch) {
		super(Family.getFor(PositionComponent.class, TextureComponent.class));
		
		pm = ComponentMapper.getFor(PositionComponent.class);
		tm = ComponentMapper.getFor(TextureComponent.class);
		
		comparator = new Comparator<Entity>() {
			@Override
			public int compare(Entity entityA, Entity entityB) {
				return (int)Math.signum(pm.get(entityB).z -
										pm.get(entityA).z);
			}
		};
		
		this.batch = batch;
		renderQueue = new Array<Entity>();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);

		renderQueue.sort(comparator);
		
		batch.begin();
		for(Entity entity : renderQueue) {
			texture = tm.get(entity).textureRegion;
			position = pm.get(entity);
			batch.draw(texture, position.x, position.y, 
					texture.getRegionWidth()/2, texture.getRegionHeight()/2, 
					texture.getRegionWidth(), texture.getRegionHeight(), 
					1, 1, position.rotation);
		}
		batch.end();
		renderQueue.clear();
	}
	
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}

}
