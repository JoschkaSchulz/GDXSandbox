package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.protonet.components.OptionsComponent;
import de.potoopirate.protonet.components.StageComponent;

public class RenderSystem extends IteratingSystem {
	private BitmapFont font;
	private Engine engine;

	private ComponentMapper<StageComponent> stageMapper;
	private Array<StageComponent> stages;
	
	private ComponentMapper<OptionsComponent> optionsMapper;
	private ImmutableArray<Entity> optionsArray;
	private OptionsComponent options;
	
	public RenderSystem() {
		super(Family.getFor(StageComponent.class));
		
		font = new BitmapFont();
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	
		stageMapper = ComponentMapper.getFor(StageComponent.class);
		optionsMapper = ComponentMapper.getFor(OptionsComponent.class);
		
		stages = new Array<StageComponent>();
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		optionsArray = engine.getEntitiesFor(Family.getFor(OptionsComponent.class));
		for(int i = 0 ; i < optionsArray.size(); i++) {
			options = optionsMapper.get(optionsArray.get(i));
		}
		
		for(StageComponent stageComponent : stages) {
			stageComponent.stage.act(deltaTime);
			stageComponent.stage.draw();
		
			stageComponent.stage.getBatch().begin();
			font.draw(stageComponent.stage.getBatch(), "Host: " + options.host, 20, Gdx.graphics.getHeight()-15);
			font.draw(stageComponent.stage.getBatch(), "Group: " + options.group, 20, Gdx.graphics.getHeight()-30);
			stageComponent.stage.getBatch().end();
		}
		
		stages.clear();
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		stages.add(stageMapper.get(entity));
	}
}
