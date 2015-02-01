package de.potoopirate.kryonet;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.kryonet.systems.KryonetClientSystem;
import de.potoopirate.kryonet.systems.KryonetServerSystem;
import de.potoopirate.kryonet.systems.RenderSystem;

public class KryonetTest extends ScreenAdapter {
	
	private SpriteBatch batch;
	private Engine engine;
	
	public KryonetTest(SpriteBatch batch) {
		this.batch = batch;
		
		engine = new Engine();
	}

	@Override
	public void render(float delta) {
		engine.update(delta);
		super.render(delta);
		
		if(Gdx.input.isKeyPressed(Keys.S) && engine.getSystem(KryonetServerSystem.class) == null) {
			System.out.println("Added a Server:");
			engine.addSystem(new KryonetServerSystem());
		}else if(Gdx.input.isKeyPressed(Keys.C) && engine.getSystem(KryonetServerSystem.class) == null) {
			System.out.println("Added a Client:");
			engine.addSystem(new KryonetClientSystem());
			engine.addSystem(new RenderSystem());
		}
	}
}
