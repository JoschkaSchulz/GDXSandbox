package de.potoopirate.ashley;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;

public class HighscoreScreen extends ScreenAdapter {

	private AshleyTest game;
	
	public HighscoreScreen(AshleyTest game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		if(Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isTouched()) {
			game.startGame();
		}
	}
}
