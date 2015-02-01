package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.ashley.component.LifetimeComponent;

public class LifetimeSystem extends IteratingSystem {

	private ComponentMapper<LifetimeComponent> lifetimeMapper;
	private LifetimeComponent lifetime;
	private Engine engine;
	private Array<Entity> removeList;
	
	public LifetimeSystem() {
		super(Family.getFor(LifetimeComponent.class));
		
		removeList = new Array<Entity>();
		lifetimeMapper = ComponentMapper.getFor(LifetimeComponent.class);
	}

	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		lifetime = lifetimeMapper.get(entity);
		lifetime.lifetimeCounter += deltaTime;
		if(lifetime.lifetime <= lifetime.lifetimeCounter) {
			removeList.add(entity);
		}
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		for(int i = 0; i < removeList.size; i++) {
			engine.removeEntity(removeList.get(i));
		}
	}
}
