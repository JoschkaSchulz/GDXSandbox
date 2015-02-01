package de.potoopirate.ashley;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.potoopirate.ashley.entity.ExplosionEntity;
import de.potoopirate.ashley.entity.FieldEntity;
import de.potoopirate.ashley.entity.PlayerEntity;
import de.potoopirate.ashley.entitysystems.CollisionSystem;
import de.potoopirate.ashley.entitysystems.DebugSystem;
import de.potoopirate.ashley.entitysystems.EnemyControllSystem;
import de.potoopirate.ashley.entitysystems.FieldCollisionSystem;
import de.potoopirate.ashley.entitysystems.FieldRenderSystem;
import de.potoopirate.ashley.entitysystems.LifetimeSystem;
import de.potoopirate.ashley.entitysystems.MovementSystem;
import de.potoopirate.ashley.entitysystems.PlayerControllSystem;
import de.potoopirate.ashley.entitysystems.PlayerSystem;
import de.potoopirate.ashley.entitysystems.PointsRenderSystem;
import de.potoopirate.ashley.entitysystems.SpawnSystem;
import de.potoopirate.ashley.entitysystems.TextureRenderSystem;

public class GameScreen extends ScreenAdapter{

	private DebugSystem debug;
	
	private AshleyTest game;
	
	public Engine engine;
	private ShapeRenderer renderer;
	private PlayerEntity player;
	private SpriteBatch batch;
	
	public GameScreen(SpriteBatch batch, AshleyTest game) {
		this.batch = batch;
		this.game = game;
		
		renderer = new ShapeRenderer();
		engine = new Engine();
		addSystems();
	}
	
	public void startNewGame() {
		addEntities();
		engine.addSystem(new SpawnSystem());
	}
	
	private void addEntities() {
		player = new PlayerEntity();
		engine.addEntity(player);
		engine.addEntity(new FieldEntity());
	}
	
	private void addSystems() {
		engine.addSystem(new PlayerControllSystem());
		engine.addSystem(new EnemyControllSystem());
		engine.addSystem(new MovementSystem());
		engine.addSystem(new CollisionSystem());
		engine.addSystem(new FieldCollisionSystem());
		engine.addSystem(new TextureRenderSystem(batch));
		engine.addSystem(new PointsRenderSystem(batch));
		engine.addSystem(new FieldRenderSystem(renderer));
		engine.addSystem(new PlayerSystem(this));
		engine.addSystem(new LifetimeSystem());
		//engine.addSystem(new DebugSystem());
	}

	public void showHighscore() {
		game.showHighscore();
	}
	
	@Override
	public void render(float delta) {
		engine.update(delta);
	}

}
