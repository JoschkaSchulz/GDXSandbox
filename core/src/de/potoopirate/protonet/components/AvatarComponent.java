package de.potoopirate.protonet.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AvatarComponent extends Component {
	public static final Texture NO_AVATAR = new Texture(Gdx.files.internal("avatar.jpg"));
	
	public int userId;
	public boolean active;
	public String texturePath;
	public Texture avatarTexture = NO_AVATAR;
	public TextureRegion avatar = new TextureRegion(NO_AVATAR);
	public Image avatarImage = new Image(NO_AVATAR);
	public boolean loaded;
	public Color lineColor;
	public float decreasteCounter;
	
	public AvatarComponent(int userId, boolean active) {
		this.active = active;
		this.userId = userId;
		loaded = false;
		decreasteCounter = 0;
		lineColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1f);
		avatarImage.setSize(120, 120);
		avatarImage.setOrigin(60, 60);
	}
}
