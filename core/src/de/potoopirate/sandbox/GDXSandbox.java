package de.potoopirate.sandbox;

import java.util.Arrays;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import de.potoopirate.ashley.AshleyTest;
import de.potoopirate.box2d.PhysicsTest;
import de.potoopirate.kryonet.KryonetTest;
import de.potoopirate.miner.world.GameWorld;
import de.potoopirate.miner.world.GameWorldTile;
import de.potoopirate.play.GooglePlayInterface;
import de.potoopirate.potoodemo.PotooDemo;
import de.potoopirate.protonet.ProtonetScreen;

public class GDXSandbox extends Game {
	SpriteBatch batch;
	ShapeRenderer mShapeRenderer;
	Texture img;
	GooglePlayInterface googlePlay;
	GameWorld mGameWorld;
	GameWorldTile[][] mGameWorldTiles;
	
	AshleyTest ashleyTest;
	ProtonetScreen protonet;
	Screen physicsScreen;
	
	PotooDemo demo;
	
	public GDXSandbox(GooglePlayInterface gp) {
		googlePlay = gp;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		//googlePlay.helloWorld();
		//mGameWorld = GameWorld.getInstance(10, 10);
		mShapeRenderer = new ShapeRenderer();
		
		// - Box2D
		physicsScreen = new PhysicsTest();
		setScreen(physicsScreen);
		
		// - Game
		//ashleyTest = new AshleyTest(batch, this);
		
		// - Test
		//demo = new PotooDemo(batch);
		
		// - Protonet
		//protonet = new ProtonetScreen();
		//setScreen(protonet);
		
		// - Kryonet
		//setScreen(new KryonetTest(batch));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		
		//OLD: ashleyTest.update(Gdx.graphics.getDeltaTime());
		//demo.update(Gdx.graphics.getDeltaTime());
		
//		mShapeRenderer.begin(ShapeType.Filled);
//		mGameWorldTiles = mGameWorld.getWorldTiles();
//		for (int w = 0; w < mGameWorldTiles.length; w++) {
//			for (int h = 0; h < mGameWorldTiles[0].length; h++) {
//				mShapeRenderer.rectLine(w, h, w+20, h+20, 1);
//			}
//		}
//		mShapeRenderer.end();
	}
}
