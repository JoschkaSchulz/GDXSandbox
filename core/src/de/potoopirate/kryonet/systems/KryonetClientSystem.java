package de.potoopirate.kryonet.systems;

import java.io.IOException;
import java.net.InetAddress;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import de.potoopirate.kryonet.Network;
import de.potoopirate.kryonet.Network.Connect;
import de.potoopirate.kryonet.Network.Position;
import de.potoopirate.kryonet.components.PlayerComponent;
import de.potoopirate.kryonet.components.PositionComponent;
import de.potoopirate.kryonet.entitys.PlayerEntity;

public class KryonetClientSystem extends EntitySystem {
	private Client client;
	
	private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<PlayerComponent> playerMapper = ComponentMapper.getFor(PlayerComponent.class);
	private PlayerComponent player;
	private PositionComponent position;
	private ImmutableArray<Entity> players;
	private Position pos;
	private boolean foundPos;
	private Entity found;
	
	private Engine engine;

	@Override
	public void addedToEngine(Engine e) {
		super.addedToEngine(e);
		this.engine = e;
		
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
						client.sendTCP(con);
					}
					
					if (object instanceof Position) {
						players = engine.getEntitiesFor(Family.getFor(PlayerComponent.class, PositionComponent.class));
						pos = (Position) object;
						
						foundPos = false;
						for(int i = 0; i < players.size(); i++) {
							player = playerMapper.get(players.get(i));
							if(player.id == pos.id) {
								foundPos = true;
								found = players.get(i);
							}
						}
						
						if(foundPos) {
							position = positionMapper.get(found);
							position.x = pos.x;
							position.y = pos.y;
						} else {
							System.out.println("add entity");
							engine.addEntity(new PlayerEntity(pos.id, pos.color));
						}
					}
				}
			});
			client.connect(60, "localhost", 50505);
			Connect c = new Connect();
			c.id = -1;
			client.sendTCP(c);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}	
}
