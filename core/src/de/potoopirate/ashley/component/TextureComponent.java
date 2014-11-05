package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {
	public static final Texture PLAYER_IMAGE = new Texture(Gdx.files.internal("gfx/player.png"));
	public static final Texture ENEMY_IMAGE = new Texture(Gdx.files.internal("gfx/enemy.png"));
	public TextureRegion textureRegion = new TextureRegion(ENEMY_IMAGE);
	
	public TextureComponent() {
		
	}
	
	public TextureComponent(Texture texture) {
		this.textureRegion = new TextureRegion(texture);
	}
}
