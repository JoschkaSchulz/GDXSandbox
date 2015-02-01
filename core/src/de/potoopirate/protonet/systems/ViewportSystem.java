package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.CameraComponent;
import de.potoopirate.protonet.components.PositionComponent;
import de.potoopirate.protonet.components.StageComponent;

public class ViewportSystem extends IteratingSystem {

	private ComponentMapper<AvatarComponent> avatarMapper;
	private AvatarComponent avatar;
	
	private ComponentMapper<PositionComponent> positionMapper;
	private PositionComponent position;
	
	private ComponentMapper<StageComponent> stageMapper;
	private Entity stageEntity;
	private ImmutableArray<Entity> stages;
	private Stage stage;
	
	private Engine engine;
	private int targetId;
	private int newTargetId;
	private Bezier<Vector3> curve;
	
	private ShapeRenderer batch = new ShapeRenderer();
	
	public ViewportSystem() {
		super(Family.getFor(AvatarComponent.class, PositionComponent.class));
		targetId = 0;
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
		
		batch.begin(ShapeType.Filled);
		if(curve != null) {
			Vector3 out = new Vector3();
			for(float i = 0f; i < 100f; i++) {
				curve.derivativeAt(out, i/100f);
				batch.point(out.x, out.y, 100f);
			}
		}
		batch.end();
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		avatar = avatarMapper.get(entity);
		if (avatar.active) {
			position = positionMapper.get(entity);
			newTargetId = avatar.userId;
			if (targetId == 0) targetId = newTargetId;
		}
		
		if (targetId != newTargetId) {
			Vector3 camPosition = stage.getViewport().getCamera().position;
			Vector3 avatarPosition = new Vector3(avatar.avatarImage.getX(), avatar.avatarImage.getY(), 0);//position.position;
			curve = new Bezier<Vector3>(camPosition, avatarPosition);
			
			Vector3 out = new Vector3();
			curve.derivativeAt(out, 1f);
			stage.getViewport().getCamera().lookAt(avatarPosition); //position.set(out);
		}
	}

}
