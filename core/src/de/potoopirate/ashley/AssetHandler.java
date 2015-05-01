package de.potoopirate.ashley;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetHandler{
	public static Texture PLAYER_IMAGE;
	public static Texture ENEMY_IMAGE;
	public static Texture EXPLOSION;
	
	public static BitmapFont BACKGROUND_FONT;
	public static BitmapFont MENU_FONT;
	
	static public void loadObjects() {
		PLAYER_IMAGE = new Texture(Gdx.files.internal("gfx/player.png"));
		ENEMY_IMAGE = new Texture(Gdx.files.internal("gfx/enemy.png"));
		EXPLOSION = new Texture(Gdx.files.internal("gfx/explosion.png"));
		
		BACKGROUND_FONT = new BitmapFont();
		BACKGROUND_FONT.scale(20f);
		
		MENU_FONT = new BitmapFont();
	}
	
	static public void dispose() {
		PLAYER_IMAGE.dispose();
		ENEMY_IMAGE.dispose();
		EXPLOSION.dispose();
		
		BACKGROUND_FONT.dispose();
		MENU_FONT.dispose();
	}
}
