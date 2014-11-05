package de.potoopirate.ashley.entity;

import com.badlogic.ashley.core.Entity;

import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.EnemyComponent;
import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;

public class EnemyEntity extends Entity {
	public EnemyEntity(int x, int y, int speed) {
		add(new MovementComponent(speed));
		add(new PositionComponent(x,y));
		add(new TextureComponent());
		add(new EnemyComponent());
		add(new CollisionComponent(10));
	}
}
