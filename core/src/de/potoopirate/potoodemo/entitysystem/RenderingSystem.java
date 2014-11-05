package de.potoopirate.potoodemo.entitysystem;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.potoodemo.components.PositionComponent;
import de.potoopirate.potoodemo.components.TextureComponent;

public class RenderingSystem extends IteratingSystem {

	private Array<Entity> renderList;
	private SpriteBatch batch;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
	
	
	public RenderingSystem(SpriteBatch batch) {
		super(Family.getFor(PositionComponent.class, TextureComponent.class));
		
		renderList = new Array<Entity>();
		this.batch = batch;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		//renderList.clear();
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		batch.begin();
		//for(Entity entity : renderList) {
			PositionComponent position = pm.get(entity);
			TextureComponent texture = tm.get(entity);
			
			batch.draw(texture.texture, position.x, position.y);
		//}
		batch.end();
		
		//renderList.add(entity);
	}

}
