package de.potoopirate.protonet.entities;

import com.badlogic.ashley.core.Entity;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.CollisionComponent;
import de.potoopirate.protonet.components.PositionComponent;

public class User extends Entity {
	public User(int id, float x, float y) {
		this.add(new AvatarComponent(id, true));
		this.add(new PositionComponent(x, y));
		this.add(new CollisionComponent(x-50, y-50, 120, 120));
	}
}
