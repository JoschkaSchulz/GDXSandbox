package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

import de.thathalas.protonet.ProtonetWrapper;

public class ChatSimulationSystem extends EntitySystem {

	private int last;
	private float counter;
	private static float MAX_COUNTER = 3f;
	
	private ProtonetWrapper user1;
	private ProtonetWrapper user2;
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		
		last = 1;
		
		user1 = new ProtonetWrapper("http://127.0.0.1:3001"); 
		user1.getToken("joschka@protonet.info", "123456789", "libGDX activity board (Simulation)");
		
		user2 = new ProtonetWrapper("http://127.0.0.1:3001"); 
		user2.getToken("punch@chuck.norris", "12345678", "libGDX activity board (Simulation)");
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		counter += deltaTime;
		if(counter >= MAX_COUNTER) {
			switch(last) {
				case 1:
					user1.getMeeps().createProjects(4, "nein du!");
					last = 2;
					break;
				case 2:
					user2.getMeeps().createProjects(4, "ne du!");
					last = 1;
					break;
			}
			
			counter = 0;
		}
	}
	
}
