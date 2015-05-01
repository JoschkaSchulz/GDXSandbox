package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.StageComponent;

public class RenderConnectionSystem extends IteratingSystem {

	private ComponentMapper<AvatarComponent> avatarMapper;
	private AvatarComponent avatar, otherAvatar;
	private ImmutableArray<Entity> avatars;
	private ComponentMapper<StageComponent> stageMapper;
	private Stage stage;
	private float sX, sY;
	private Engine engine;
	private ShapeRenderer renderer;
	
	public RenderConnectionSystem() {
		super(Family.getFor(AvatarComponent.class));
	}

	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
		
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
		stageMapper = ComponentMapper.getFor(StageComponent.class);
		renderer = new ShapeRenderer();
		
		stage = stageMapper.get(engine.getEntitiesFor(Family.getFor(StageComponent.class)).first()).stage;
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		avatar = avatarMapper.get(entity);
		avatars = engine.getEntitiesFor(Family.getFor(AvatarComponent.class));
		renderer.setProjectionMatrix(stage.getCamera().combined);
		sX = stage.getRoot().getX();
		sY = stage.getRoot().getY();
		renderer.begin(ShapeType.Line);
		for(int i = 0; i < avatars.size(); i++) {
			otherAvatar = avatarMapper.get(avatars.get(i));
			renderer.line(
					sX + avatar.avatarImage.getCenterX(), 
					sY + avatar.avatarImage.getCenterY(), 
					sX + otherAvatar.avatarImage.getCenterX(), 
					sY + otherAvatar.avatarImage.getCenterY(),
					avatar.lineColor,
					otherAvatar.lineColor);
		}
		renderer.end();
	}

}
