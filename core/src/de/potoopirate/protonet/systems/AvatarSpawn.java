package de.potoopirate.protonet.systems;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.CollisionComponent;
import de.potoopirate.protonet.components.PositionComponent;
import de.potoopirate.protonet.entities.User;
import de.thathalas.protonet.ProtonetWrapper;
import de.thathalas.protonet.objects.ProtonetUser;

public class AvatarSpawn extends EntitySystem {

	private float delta;
	private ProtonetWrapper protonet;
	private ProtonetUser user;
	private List<String> names;
	private ImmutableArray<Entity> users;
	private ComponentMapper<AvatarComponent> avatarMapper;
	private Engine engine;
	private User userEntity;
	
	public AvatarSpawn() {
		
	}
	
	public AvatarSpawn(ProtonetWrapper protonet) {
		this.protonet = protonet;
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
		delta = 0f;
		names = new ArrayList<String>();
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		delta += deltaTime;
		if(delta > 1) {
			user = protonet.getProjects().show(4).getLastMeepUser();
			if(!names.contains(user.getFirstName())) {
				System.out.println("-- found --> " + user.getFirstName());
				names.add(user.getFirstName());
				userEntity = new User(user.getId(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
				engine.addEntity(userEntity);
			}
			
			users = engine.getEntitiesFor(Family.getFor(AvatarComponent.class, PositionComponent.class, CollisionComponent.class));
			for(int i = 0; i < users.size(); i++) {
				if (((AvatarComponent) avatarMapper.get(users.get(i))).userId == user.getId()) {
					((AvatarComponent) avatarMapper.get(users.get(i))).active = true;
					System.out.println("USER => " + user.getFirstName());
				} else {
					((AvatarComponent) avatarMapper.get(users.get(i))).active = false;
				}
				
			}
			
			
			delta = 0;
		}
	}

}
