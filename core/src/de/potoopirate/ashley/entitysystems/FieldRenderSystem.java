package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.FieldComponent;
import de.potoopirate.ashley.component.PositionComponent;

public class FieldRenderSystem extends IteratingSystem {

	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<CollisionComponent> collisionMapper;
	
	private PositionComponent position;
	private CollisionComponent collision;
	
	private ShapeRenderer renderer;
	
	public FieldRenderSystem(ShapeRenderer renderer) {
		super(Family.getFor(FieldComponent.class, PositionComponent.class, CollisionComponent.class));
		
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
		
		this.renderer = renderer;
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		position = positionMapper.get(entity);
		collision = collisionMapper.get(entity);
		
		renderer.begin(ShapeType.Line);
		renderer.circle(position.x, position.y, collision.circleRadius);
		renderer.end();
	}

}
