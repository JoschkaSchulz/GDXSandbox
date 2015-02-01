package de.potoopirate.protonet.systems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.json.JSONObject;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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
	
	private ProtonetWrapper protonet;
	private ProtonetUser user;
	private Engine engine;

	public AvatarLoadSystem() {
		super(Family.getFor(AvatarComponent.class));
		
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}

	public AvatarLoadSystem(ProtonetWrapper protonet) {
		super(Family.getFor(AvatarComponent.class));
		
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
		stageMapper = ComponentMapper.getFor(StageComponent.class);
		this.protonet = protonet;
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		avatarComponent = avatarMapper.get(entity);
		
		if(avatarComponent.avatarTexture == null || avatarComponent.avatarTexture == AvatarComponent.NO_AVATAR) {
			try {
				user = protonet.getUsers().show(avatarComponent.userId);
				URL url = new URL(user.getAvatar());
				ReadableByteChannel rbc = Channels.newChannel(url.openStream());
				FileOutputStream fos = new FileOutputStream("assets/avatar_"+user.getId()+".jpg");
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				avatarComponent.avatarTexture = new Texture(Gdx.files.internal("assets/avatar_"+user.getId()+".jpg"));
				avatarComponent.avatar = new TextureRegion(avatarComponent.avatarTexture);
				avatarComponent.avatarImage = new Image(avatarComponent.avatarTexture);
				avatarComponent.avatarImage.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
				stages = engine.getEntitiesFor(Family.getFor(StageComponent.class));
				for(int i = 0; i < stages.size(); i++) {
					stage = stageMapper.get(stages.get(i));
					stage.stage.addActor(avatarComponent.avatarImage);
				}
			} catch(IOException e) {
				System.err.println("Can't load Avatar, maybe there is a problem with the connection?");
			}
		}
	}
}
