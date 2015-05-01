package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.HexagonComponent;
import de.potoopirate.protonet.components.PositionComponent;
import de.potoopirate.protonet.components.StageComponent;

public class ViewportSystem extends IteratingSystem {

	private ComponentMapper<AvatarComponent> avatarMapper;
	private AvatarComponent avatar;
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<StageComponent> stageMapper;
	private Entity stageEntity;
	private ImmutableArray<Entity> stages;
	private Stage stage;
	private Engine engine;
	private int targetId;
	private int newTargetId;
	
	public ViewportSystem() {
		super(Family.getFor(AvatarComponent.class, PositionComponent.class));
		targetId = -1;
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		stageMapper = ComponentMapper.getFor(StageComponent.class);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		//Searching for the stages
		stages = engine.getEntitiesFor(Family.getFor(StageComponent.class));
		for(int i = 0; i < stages.size(); i++) {
			stageEntity = stages.get(i);
			stage = stageMapper.get(stageEntity).stage;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D)) {
			stage.getCamera().translate(10, 0, 0);
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			stage.getCamera().translate(-10, 0, 0);
		} else if (Gdx.input.isKeyPressed(Keys.W)) {
			stage.getCamera().translate(0, 10, 0);
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			stage.getCamera().translate(0, -10, 0);
		} else if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			ImmutableArray<Entity> hexagone = engine.getEntitiesFor(Family.getFor(HexagonComponent.class));
			ComponentMapper<HexagonComponent> hM = ComponentMapper.getFor(HexagonComponent.class);
			for(int i = 0; i < hexagone.size(); i++) {
				hM.get(hexagone.get(i)).raise = 1;
			}
		}
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		avatar = avatarMapper.get(entity);
		if (avatar.active) {
			newTargetId = avatar.userId;
			if (targetId == 0) targetId = newTargetId;
		
			stage.addAction(Actions.moveTo(
					-avatar.avatarImage.getX() + Gdx.graphics.getWidth()/2 - 50, 
					-avatar.avatarImage.getY() + Gdx.graphics.getHeight()/2 - 50, 
					2f, Interpolation.sine));
			avatar.active = false;
		}
	}

}
