package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.potoopirate.ashley.AssetHandler;
import de.potoopirate.ashley.component.HighscoreComponent;
import de.potoopirate.ashley.component.PlayerComponent;

public class PointsRenderSystem extends IteratingSystem {

	private static final float POINT_TIME = 1f;
	
	private ComponentMapper<HighscoreComponent> highscoreMapper;
	private Stage stage = new Stage();
	private Label pointsLabel;
	
	private HighscoreComponent highscore;
	private float counter;
	private int points;
	private TextBounds textBounds;
	private Engine engine;
	private ImmutableArray<Entity> players;
	private SpriteBatch batch;
	
	
	public PointsRenderSystem(SpriteBatch batch) {
		super(Family.getFor(HighscoreComponent.class));
		
		highscoreMapper = ComponentMapper.getFor(HighscoreComponent.class);
		counter = 0;
		points = 0;
		this.batch = batch;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		counter += deltaTime;
	
		batch.begin();
		textBounds = AssetHandler.BACKGROUND_FONT.getBounds(""+points);
		AssetHandler.BACKGROUND_FONT.draw(batch, ""+points, 
				Gdx.graphics.getWidth()/2 - textBounds.width/2, 
				Gdx.graphics.getHeight()/2 + textBounds.height/2);
		batch.end();
	}



	@Override
	public void processEntity(Entity entity, float deltaTime) {
		highscore = highscoreMapper.get(entity);
		
		if(counter > POINT_TIME) {
			counter = 0;
			points = highscore.points++;
		}
	}

}
