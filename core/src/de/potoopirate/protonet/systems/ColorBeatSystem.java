package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.potoopirate.protonet.components.HexagonComponent;

public class ColorBeatSystem extends IteratingSystem {

	private ComponentMapper<HexagonComponent> hexagonMapper;
	private HexagonComponent hexagon;
	
	public ColorBeatSystem() {
		super(Family.getFor(HexagonComponent.class));
		hexagonMapper = ComponentMapper.getFor(HexagonComponent.class);
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		hexagon = hexagonMapper.get(entity);
		
		//decrease
		if(hexagon.raise <= 0f) {
			hexagon.r -= deltaTime/10f*2;
			if(hexagon.r < 0.1f) hexagon.r = 0.1f;
			hexagon.g -= deltaTime/10f;
			if(hexagon.g < 0.1f) hexagon.g = 0.1f;
			hexagon.b += deltaTime/10f;
			if(hexagon.b > 0.1f) hexagon.b = 0.1f;
			
			if(Math.random() >= 0.999f && hexagon.b == 0.1f && hexagon.g == 0.1f && hexagon.b == 0.1f) {
				hexagon.r = 0.1f;
				hexagon.g = 0.1f;
				hexagon.b = 0;
				hexagon.raise = 1f;
			}
		} else {
			hexagon.raise -= deltaTime/5f;
			if(hexagon.raise <= 0f) hexagon.raise = 0f;
			
			hexagon.r += deltaTime/10f*2;
			if(hexagon.r >= 1f) hexagon.r = 1f;
			hexagon.g += deltaTime/10f;
			if(hexagon.g > 0.5f) hexagon.g = 0.5f;
		}
	}

}
