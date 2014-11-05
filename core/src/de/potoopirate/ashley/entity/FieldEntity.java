package de.potoopirate.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.FieldComponent;
import de.potoopirate.ashley.component.PositionComponent;

public class FieldEntity extends Entity {
	public FieldEntity() {
		add(new FieldComponent());
		add(new CollisionComponent(Gdx.graphics.getHeight()/2));
		add(new PositionComponent(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
	}
}
