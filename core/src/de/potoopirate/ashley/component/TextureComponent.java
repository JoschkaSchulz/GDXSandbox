package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.potoopirate.ashley.AssetHandler;

public class TextureComponent extends Component {
	public TextureRegion textureRegion = new TextureRegion(AssetHandler.ENEMY_IMAGE);
	
	public TextureComponent() {
		
	}
	
	public TextureComponent(Texture texture) {
		this.textureRegion = new TextureRegion(texture);
	}
}
