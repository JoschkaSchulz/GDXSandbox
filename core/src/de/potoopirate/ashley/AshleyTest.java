package de.potoopirate.ashley;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AshleyTest {
	
	private Game game;
	
	private GameScreen gameScreen;
	private HighscoreScreen highscoreScreen;
	
	public AshleyTest(SpriteBatch batch, Game game) {
		this.game = game;
		gameScreen = new GameScreen(batch, this);
		highscoreScreen = new HighscoreScreen(this);
		startGame();
	}
	
	public void startGame() {
		game.setScreen(gameScreen);
		gameScreen.startNewGame();
	}
	
	public void showHighscore() {
		game.setScreen(highscoreScreen);
	}
}
