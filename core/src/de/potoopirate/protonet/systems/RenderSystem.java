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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.potoopirate.protonet.components.CameraComponent;
import de.potoopirate.protonet.components.StageComponent;

public class RenderSystem extends IteratingSystem {
	private Array<StageComponent> stages;
	private ComponentMapper<StageComponent> stageMapper;
	
	public RenderSystem() {
		super(Family.getFor(StageComponent.class));
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
	
		stageMapper = ComponentMapper.getFor(StageComponent.class);
		
		stages = new Array<StageComponent>();
		
		Entity entity = new Entity();
		entity.add(new StageComponent());
		engine.addEntity(entity);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
				
		for(StageComponent stageComponent : stages) {
			stageComponent.stage.act(deltaTime);
			stageComponent.stage.draw();
		}
		
		stages.clear();
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		stages.add(stageMapper.get(entity));
	}
}
