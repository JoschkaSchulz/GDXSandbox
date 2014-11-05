package de.potoopirate.kryonet.systems;

import java.io.IOException;
import java.net.InetAddress;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import de.potoopirate.kryonet.Network;
import de.potoopirate.kryonet.Network.Connect;

public class KryonetClientSystem extends EntitySystem {
	private Client client;

	@Override
	public void addedToEngine(Engine engine) {
		super.addedToEngine(engine);
		
		try {
			client = new Client();
			client.start();
			Network.register(client);
			client.addListener(new Listener() {

				@Override
				public void received(Connection connection, Object object) {
					super.received(connection, object);
					
					if (object instanceof Connect) {
						Connect con = (Connect) object;
						System.out.println("[CLIENT]: Regestriert mit der Spielernummer " + con.id);
					}
				}
			});
			client.connect(60, "localhost", 50505);
			Connect c = new Connect();
			c.id = -1;
			client.sendTCP(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	}
	
	
}
