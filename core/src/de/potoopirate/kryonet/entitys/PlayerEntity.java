package de.potoopirate.kryonet.entitys;

import com.badlogic.ashley.core.Entity;

import de.potoopirate.kryonet.components.PlayerComponent;
import de.potoopirate.kryonet.components.PositionComponent;

public class PlayerEntity extends Entity {
	public PlayerEntity(int id) {
		add(new PlayerComponent(id, "Player " + id));
		add(new PositionComponent(100, 100));
	}
}
