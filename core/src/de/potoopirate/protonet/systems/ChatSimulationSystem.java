package de.potoopirate.protonet.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

import de.thathalas.protonet.ProtonetWrapper;

public class ChatSimulationSystem extends EntitySystem {
	private ProtonetWrapper user1;
	private ProtonetWrapper user2;
	private ProtonetWrapper user3;
	
	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		

		user1 = new ProtonetWrapper("http://127.0.0.1:3001"); 
		user1.getToken("joschka@protonet.info", "123456789", "libGDX activity board (Simulation)");
		Chatter user1Chatter = new Chatter(user1);
		user1Chatter.start();
		
		user2 = new ProtonetWrapper("http://127.0.0.1:3001"); 
		user2.getToken("punch@chuck.norris", "12345678", "libGDX activity board (Simulation)");
		Chatter user2Chatter = new Chatter(user2);
		user2Chatter.start();
		
		user3 = new ProtonetWrapper("http://127.0.0.1:3001"); 
		user3.getToken("test@chuck.testa", "12345678", "libGDX activity board (Simulation)");
		Chatter user3Chatter = new Chatter(user3);
		user3Chatter.slowDown = 45000;
		user3Chatter.start();
	}
	
	class Chatter extends Thread {

		public ProtonetWrapper protonet;
		public int slowDown;
		
		public Chatter(ProtonetWrapper protonet) {
			this.protonet = protonet;
			slowDown = 0;
		}
		
		@Override
		public void run() {
			super.run();
			while(true) {
				protonet.getMeeps().createProjects(4, "miep :D");
				try {
					Thread.sleep((long) (Math.random()*5000)+7000 + slowDown);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
