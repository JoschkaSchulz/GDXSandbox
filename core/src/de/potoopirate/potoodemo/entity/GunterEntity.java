package de.potoopirate.potoodemo.entity;

import com.badlogic.ashley.core.Entity;

import de.potoopirate.potoodemo.components.PositionComponent;
import de.potoopirate.potoodemo.components.TextureComponent;

public class GunterEntity extends Entity {
	public GunterEntity() {
		add(new PositionComponent());
		add(new TextureComponent());
	}
}
