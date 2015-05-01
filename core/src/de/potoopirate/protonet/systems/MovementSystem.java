package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.CollisionComponent;
import de.potoopirate.protonet.components.PositionComponent;

public class MovementSystem extends EntitySystem {
	
	private Engine engine;
	
	private ImmutableArray<Entity> users;
	private Entity user1, user2;
	private PositionComponent position1, position2;
	private Vector3 pos;
	private float xPos, yPos;
	private CollisionComponent collision1, collision2;
	private AvatarComponent avatar;
	
	private ComponentMapper<PositionComponent> positionMapper;
	private ComponentMapper<CollisionComponent> collisionMapper;
	private ComponentMapper<AvatarComponent> avatarMapper;
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
		
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		users = engine.getEntitiesFor(Family.getFor(AvatarComponent.class, CollisionComponent.class, PositionComponent.class));
		
		for(int x = 0; x < users.size(); x++) {
			user1 = users.get(x);
			for(int y = 0; y < users.size(); y++) {
				user2 = users.get(y);
				if(user1 != user2) {
					position1 = positionMapper.get(user1);
					position2 = positionMapper.get(user2);
					collision1 = collisionMapper.get(user1);
					collision2 = collisionMapper.get(user2);
					if(Intersector.overlaps(collision1.boundingBox, collision2.boundingBox)) {
						pos = position2.position;
						xPos = (float)((Math.random()*-1000)+500+pos.x);
						yPos = (float)((Math.random()*-1000)+500+pos.y);
						position2.position.set(xPos-50, yPos-50, 0);
						collision2.boundingBox.setX(xPos-50);
						collision2.boundingBox.setY(yPos-50);
						avatar = avatarMapper.get(user2);
						avatar.avatarImage.addAction(Actions.sequence(Actions.moveTo(xPos, yPos, 1f), Actions.run(new Runnable() {	
								@Override
								public void run() {
									avatar.active = true;
								}
							})
						));
					}
				}
			}
		}
	}
}
