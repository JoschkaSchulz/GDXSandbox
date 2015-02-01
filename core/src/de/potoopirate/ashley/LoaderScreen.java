package de.potoopirate.ashley;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoaderScreen extends ScreenAdapter {
	
	public static final int STATE_WAITING = 0;
	public static final int STATE_OBJECTS = 1;
	
	private int state;
	private SpriteBatch batch;
	private AshleyTest game;
	
	public LoaderScreen(SpriteBatch batch, AshleyTest game) {
		state = STATE_WAITING;
		
		this.batch = batch;
		this.game = game;
	}
	
	public boolean startLoading() {
		state = STATE_OBJECTS;
		AssetHandler.loadObjects();
		return true;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}
}
