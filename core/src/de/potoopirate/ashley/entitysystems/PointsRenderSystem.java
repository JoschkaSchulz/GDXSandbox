package de.potoopirate.ashley.entitysystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.potoopirate.ashley.component.PlayerComponent;

public class PointsRenderSystem extends IteratingSystem {

	private static final float POINT_TIME = 1f;
	
	private ComponentMapper<PlayerComponent> playerMapper;
	private Stage stage = new Stage();
	private Label pointsLabel;
	
	private PlayerComponent p;
	private float counter;
	private Engine engine;
	private ImmutableArray<Entity> players;
	
	
	public PointsRenderSystem(SpriteBatch batch) {
		super(Family.getFor(PlayerComponent.class));
		
		playerMapper = ComponentMapper.getFor(PlayerComponent.class);
		counter = 0;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		counter += deltaTime;
	}



	@Override
	public void processEntity(Entity entity, float deltaTime) {
		p = playerMapper.get(entity);
		
		if(counter > POINT_TIME) {
			counter = 0;
			p.points++;
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nPunkte: " + p.points);
		}
	}

}
