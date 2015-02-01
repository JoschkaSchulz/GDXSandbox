package de.potoopirate.ashley;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetHandler{
	public static Texture PLAYER_IMAGE;
	public static Texture ENEMY_IMAGE;
	public static Texture EXPLOSION;
	
	static public void loadObjects() {
		PLAYER_IMAGE = new Texture(Gdx.files.internal("gfx/player.png"));
		ENEMY_IMAGE = new Texture(Gdx.files.internal("gfx/enemy.png"));
		EXPLOSION = new Texture(Gdx.files.internal("gfx/explosion.png"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void dispose() {
		PLAYER_IMAGE.dispose();
		ENEMY_IMAGE.dispose();
		EXPLOSION.dispose();
	}
}
