package de.potoopirate.kryonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.kryonet.components.PlayerComponent;
import de.potoopirate.kryonet.components.PositionComponent;

public class RenderSystem extends IteratingSystem {

	private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
	private PositionComponent position;
	
	private Array<Entity> renderQueue;
	
	ShapeRenderer renderer;
	
	public RenderSystem() {
		super(Family.getFor(PlayerComponent.class, PositionComponent.class));
		renderer = new ShapeRenderer();
		renderQueue = new Array<Entity>();
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		renderer.begin(ShapeType.Line);
		for(Entity entity : renderQueue) {
			position = positionMapper.get(entity);
			
			if(position.color != null) {
				renderer.setColor(position.color);
				renderer.circle(position.x, position.y, 15);
				renderer.setColor(0, 0, 0, 1);
			}
		}
		renderer.end();
		renderQueue.clear();
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		renderQueue.add(entity);
	}

}
