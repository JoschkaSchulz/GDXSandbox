package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;

import de.potoopirate.ashley.entity.EnemyEntity;

public class SpawnSystem extends EntitySystem {

	private static final float SPAWN_TIME = 5f;
	
	private float counter = 0;
	private Engine engine;
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		counter += deltaTime;
		
		if(counter > SPAWN_TIME) {
			counter = 0;
			int rand = (int)(Math.random()*4);
			switch(rand) {
			case 0:
				engine.addEntity(new EnemyEntity(0, 0, 50));
				break;
			case 1:
				engine.addEntity(new EnemyEntity(0, Gdx.graphics.getHeight(), 50));
				break;
			case 2:
				engine.addEntity(new EnemyEntity(Gdx.graphics.getWidth(), 0, 50));
				break;
			case 3:
				engine.addEntity(new EnemyEntity(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 50));
				break;
			}
		}
	}
}
