package de.potoopirate.potoodemo.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {
	static Texture tex = new Texture(Gdx.files.internal("gfx/player.png"));
	public TextureRegion texture = new TextureRegion(tex);
}
