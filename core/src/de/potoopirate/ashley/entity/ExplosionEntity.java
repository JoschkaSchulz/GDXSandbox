package de.potoopirate.ashley.entity;

import com.badlogic.ashley.core.Entity;

import de.potoopirate.ashley.AssetHandler;
import de.potoopirate.ashley.component.LifetimeComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;

public class ExplosionEntity extends Entity {
	public ExplosionEntity(float x, float y) {
		add(new LifetimeComponent(0.5f));
		add(new TextureComponent(AssetHandler.EXPLOSION));
		PositionComponent position = new PositionComponent(x, y);
		position.z = 5f;
		add(position);
	}
}
