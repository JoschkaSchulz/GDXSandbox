package de.potoopirate.potoodemo;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.ashley.entitysystems.TextureRenderSystem;
import de.potoopirate.potoodemo.entity.GunterEntity;
import de.potoopirate.potoodemo.entitysystem.RenderingSystem;

public class PotooDemo {
	private SpriteBatch batch;
	private Engine engine;
	
	public PotooDemo(SpriteBatch batch) {
		this.batch = batch;
		engine = new Engine();
		
		engine.addEntity(new GunterEntity());
		
		engine.addSystem(new RenderingSystem(batch));
	}
	
	public void update(float delta) {
		engine.update(delta);
	}
}
