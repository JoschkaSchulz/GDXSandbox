package de.potoopirate.kryonet.systems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import de.potoopirate.kryonet.Network;
import de.potoopirate.kryonet.Network.Connect;
import de.potoopirate.kryonet.components.ServerCompoent;
import de.potoopirate.kryonet.entitys.PlayerEntity;

public class KryonetServerSystem extends IteratingSystem {

	public List<Connection> list;
	
	private Engine engine;
	
	public KryonetServerSystem() {
		super(Family.getFor(ServerCompoent.class));
		list = new ArrayList<Connection>();
	}

	private Server server;
	
	@Override
	public void addedToEngine(Engine e) {
		super.addedToEngine(e);
		this.engine = e;
		
		try {
			server = new Server();	
			server.start();
			Network.register(server);
			server.addListener(new Listener() {

				@Override
				public void received(Connection connection, Object object) {
					super.received(connection, object);
					
					if(object instanceof Connect) {
						Connect con = (Connect) object;
						if(con.id == -1) {
							int id = list.size();
							list.add(connection);
							engine.addEntity(new PlayerEntity(id));
						
							//answer
							Connect c = new Connect();
							c.id = id;
							connection.sendTCP(c);
						}
					}
				}
				
			});
			server.bind(50505);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		
	}
	
	
}
