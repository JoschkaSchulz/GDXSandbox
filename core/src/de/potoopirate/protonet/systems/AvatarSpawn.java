package de.potoopirate.protonet.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;

import de.potoopirate.protonet.ProtonetHelper;
import de.potoopirate.protonet.components.AvatarComponent;
import de.potoopirate.protonet.components.CollisionComponent;
import de.potoopirate.protonet.components.PositionComponent;
import de.potoopirate.protonet.entities.User;
import de.thathalas.protonet.ProtonetWrapper;
import de.thathalas.protonet.objects.ProtonetMeep;
import de.thathalas.protonet.objects.ProtonetUser;

public class AvatarSpawn extends EntitySystem {

	private float delta;
	private ProtonetWrapper protonet;
	private ProtonetUser user;
	private List<String> names;
	private List<ProtonetUser> preLoad;
	private boolean preLoadStarted;
	private ImmutableArray<Entity> users;
	private ComponentMapper<AvatarComponent> avatarMapper;
	private AvatarComponent avatar;
	private Engine engine;
	private User userEntity;
	private ProtonetUser tempUser;
	private LoadUser userLoader;
	
	public AvatarSpawn() {
		this.protonet = ProtonetHelper.getConnection();
	}
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		this.engine = engine;
		delta = 0f;
		preLoadStarted = false;
		preLoad = new ArrayList<ProtonetUser>();
		names = new ArrayList<String>();
		avatarMapper = ComponentMapper.getFor(AvatarComponent.class);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		delta += deltaTime;
		if(preLoad.isEmpty() && !preLoadStarted) {
			PreLoader preLoader = new PreLoader();
			preLoader.start();
		} else if (delta > 5f && !preLoad.isEmpty()) {
			tempUser = preLoad.remove(preLoad.size()-1);
			delta = 0;
		} else if (delta > 5f && preLoad.isEmpty()) {
			userLoader = new LoadUser();
			userLoader.start();
			delta = 0;
		}
		
		if(tempUser != null) {
			user = tempUser;
			tempUser = null;
			if(!names.contains(user.getFirstName())) {
				names.add(user.getFirstName());
				userEntity = new User(user.getId(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
				engine.addEntity(userEntity);
			}
			
			users = engine.getEntitiesFor(Family.getFor(AvatarComponent.class, PositionComponent.class, CollisionComponent.class));
			for(int i = 0; i < users.size(); i++) {
				avatar = (AvatarComponent) avatarMapper.get(users.get(i));
				if (avatar.userId == user.getId()) {
					avatar.active = true;
					avatar.avatarImage.setScale(1f);
				} else {
					avatar.active = false;
				}	
				avatar = null;
			}
		}
	}
	
	class PreLoader extends Thread {
		@Override
		public void run() {
			super.run();
			preLoadStarted = true;
			List<ProtonetMeep> meeps = protonet.getMeeps().indexProjects(4,10);
			for(ProtonetMeep meep : meeps) {
				preLoad.add(meep.getUser());
			}
		}
	}
	
	class LoadUser extends Thread {
		@Override
		public void run() {
			super.run();
			tempUser = protonet.getProjects().show(4).getLastMeepUser();
			System.out.println(tempUser.getUsername() + ":" +protonet.getProjects().show(1).getLastMeep().getMessage());
		}
	}

}
