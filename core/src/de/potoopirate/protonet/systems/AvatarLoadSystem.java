package de.potoopirate.protonet.systems;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.potoopirate.protonet.ProtonetHelper;
import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.StageComponent;
import de.thathalas.protonet.ProtonetWrapper;
import de.thathalas.protonet.objects.ProtonetUser;

public class AvatarLoadSystem extends IteratingSystem {
	
	private ComponentMapper<StageComponent> stageMapper;
	private ComponentMapper<AvatarComponent> avatarMapper;
	private AvatarComponent avatarComponent;
	private StageComponent stage;
	private ImmutableArray<Entity> stages;
	private ProtonetUser user;
	private Engine engine;

	public AvatarLoadSystem() {
		super(Family.getFor(AvatarComponent.class));
		
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
		stageMapper = ComponentMapper.getFor(StageComponent.class);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}	

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		avatarComponent = avatarMapper.get(entity);
		
		if(!avatarComponent.loaded && avatarComponent.texturePath != null) {
			setTexture(avatarComponent, avatarComponent.texturePath);
			avatarComponent.loaded = true;
		}
		
		if(avatarComponent.avatarTexture == null || avatarComponent.avatarTexture == AvatarComponent.NO_AVATAR) {
			AvatarLoader loader = new AvatarLoader(avatarComponent);
			loader.start();
			avatarComponent.avatarImage.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			stages = engine.getEntitiesFor(Family.getFor(StageComponent.class));
			for(int i = 0; i < stages.size(); i++) {
				stage = stageMapper.get(stages.get(i));
				stage.stage.addActor(avatarComponent.avatarImage);
			}
		}
	}
	
	private void setTexture(AvatarComponent avatarComponent, String path) {
		avatarComponent.avatarTexture = new Texture(Gdx.files.internal(path));
		avatarComponent.avatar = new TextureRegion(avatarComponent.avatarTexture);
		avatarComponent.avatarImage.setDrawable(new Image(avatarComponent.avatarTexture).getDrawable());
	}
	
	class AvatarLoader extends Thread {

		private URL url;
		private AvatarComponent avatarComponent;
		private ProtonetUser user;
		
		ProtonetWrapper protonet;
		
		public AvatarLoader(AvatarComponent avatarComponent) {
			super();
			this.avatarComponent = avatarComponent;
			this.protonet = ProtonetHelper.getConnection();
		}
		
		@Override
		public void run() {
			try {
				this.user = this.protonet.getUsers().show(avatarComponent.userId);
				this.url = new URL(user.getAvatar());
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream("assets/avatar_"+user.getId()+".jpg");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				avatarComponent.texturePath = "assets/avatar_"+user.getId()+".jpg";
			} catch (Exception e) {}
			super.run();
		}
		
	}
}
