package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.potoopirate.ashley.GameScreen;
import de.potoopirate.ashley.component.PlayerComponent;

public class PlayerSystem extends IteratingSystem {

	private ComponentMapper<PlayerComponent> playerMapper;
	private PlayerComponent player;
	
	private Engine engine;
	private GameScreen screen;
	
	public PlayerSystem(GameScreen screen) {
		super(Family.getFor(PlayerComponent.class));
		this.screen = screen;
		
		playerMapper = ComponentMapper.getFor(PlayerComponent.class);
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		player = playerMapper.get(entity);
		
		if(player.currentState == PlayerComponent.STATE_GAME_OVER) {
				engine.removeAllEntities();
				engine.removeSystem(engine.getSystem(SpawnSystem.class));
				screen.showHighscore();
		}
	}

}
