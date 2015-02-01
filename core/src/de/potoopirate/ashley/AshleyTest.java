package de.potoopirate.ashley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AshleyTest {
	
	private Game game;

	private LoaderScreen loaderScreen;
	private MainScreen mainScreen;
	private GameScreen gameScreen;
	private HighscoreScreen highscoreScreen;
	
	public AshleyTest(SpriteBatch batch, Game game) {
		this.game = game;
		gameScreen = new GameScreen(batch, this);
		loaderScreen = new LoaderScreen(batch, this);
		highscoreScreen = new HighscoreScreen(this);
		startLoading();
	}
	
	public void startLoading() {
		if(loaderScreen.startLoading()) {
			
		} else {
			Gdx.app.exit();
		}
	}
	
	public void startGame() {
		game.setScreen(gameScreen);
		gameScreen.startNewGame();
	}
	
	public void showHighscore() {
		game.setScreen(highscoreScreen);
	}
}
