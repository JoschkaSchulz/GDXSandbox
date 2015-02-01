package de.potoopirate.kryonet.entitys;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;

import de.potoopirate.kryonet.components.PlayerComponent;
import de.potoopirate.kryonet.components.PositionComponent;

public class PlayerEntity extends Entity {
	public PlayerEntity(int id, Color color) {
		add(new PlayerComponent(id, "Player " + id));
		add(new PositionComponent(100, 100, color));
	}
}
