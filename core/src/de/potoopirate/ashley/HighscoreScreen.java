package de.potoopirate.ashley;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.ashley.component.HighscoreComponent;

public class HighscoreScreen extends ScreenAdapter {
	private AshleyTest game;
	private SpriteBatch batch;
	private float lastPoints;
	
	private Engine engine;
	private ImmutableArray<Entity> highscores;
	private ComponentMapper<HighscoreComponent> highscoreMapper;
	private HighscoreComponent highscore;
	
	public HighscoreScreen(AshleyTest game, Engine engine) {
		this.game = game;
		batch = new SpriteBatch();
		this.engine = engine;
		highscoreMapper = ComponentMapper.getFor(HighscoreComponent.class);
	}

	private void addPointsToList() {
		highscores = engine.getEntitiesFor(Family.getFor(HighscoreComponent.class));
		System.out.println("===> Highscores count: " + highscores.size());
		highscore = highscoreMapper.get(highscores.first());
		highscore.pointList.add(highscore.points);
		lastPoints = highscore.points;
		highscore.points = 0;
	}
	
	@Override
	public void show() {
		super.show();
		addPointsToList();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		batch.begin();
		AssetHandler.MENU_FONT.draw(batch, "You got " + lastPoints, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()-20);
		batch.end();
		
		if(Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isTouched()) {
			game.startGame();
		}
	}
}
