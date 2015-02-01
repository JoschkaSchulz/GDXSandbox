package de.potoopirate.ashley.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import de.potoopirate.ashley.AssetHandler;
import de.potoopirate.ashley.component.CollisionComponent;
import de.potoopirate.ashley.component.MovementComponent;
import de.potoopirate.ashley.component.PlayerComponent;
import de.potoopirate.ashley.component.PositionComponent;
import de.potoopirate.ashley.component.TextureComponent;

public class PlayerEntity extends Entity {
	public PlayerEntity() {
		add(new PlayerComponent());
		add(new PositionComponent(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2));
		add(new MovementComponent(100));
		add(new TextureComponent(AssetHandler.PLAYER_IMAGE));
		add(new CollisionComponent(10));
	}
}
