package de.potoopirate.ashley;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.ashley.component.HighscoreComponent;

public class AshleyTest {
	
	private Game game;

	private LoaderScreen loaderScreen;
	private MainScreen mainScreen;
	private GameScreen gameScreen;
	private HighscoreScreen highscoreScreen;
	
	private Engine engine;
	
	public AshleyTest(SpriteBatch batch, Game game) {
		this.game = game;
		engine = new Engine();
		gameScreen = new GameScreen(batch, this, engine);
		loaderScreen = new LoaderScreen(batch, this);
		highscoreScreen = new HighscoreScreen(this, engine);
		
		//Adding a global component
		Entity entity = new Entity();
		entity.add(new HighscoreComponent());
		engine.addEntity(entity);
		
		game.setScreen(loaderScreen);
		startLoading();
	}
	
	public void startLoading() {
		if(loaderScreen.startLoading()) {
			startGame();
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
