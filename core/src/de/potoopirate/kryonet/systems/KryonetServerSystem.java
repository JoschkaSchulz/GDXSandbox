package de.potoopirate.kryonet.systems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import de.potoopirate.kryonet.Network;
import de.potoopirate.kryonet.Network.Connect;
import de.potoopirate.kryonet.Network.Position;
import de.potoopirate.kryonet.components.PlayerComponent;
import de.potoopirate.kryonet.components.PositionComponent;
import de.potoopirate.kryonet.components.ServerCompoent;
import de.potoopirate.kryonet.entitys.PlayerEntity;

public class KryonetServerSystem extends EntitySystem {

	public List<Connection> list;
	private ComponentMapper<PlayerComponent> playerMapper;
	private ComponentMapper<PositionComponent> positionMapper;
	
	private Engine engine;
	private ImmutableArray<Entity> players;
	private Server server;
	private PositionComponent position;
	private PlayerComponent player;
	private Position positionBroadcast;
	
	public KryonetServerSystem() {
		list = new ArrayList<Connection>();
		playerMapper = ComponentMapper.getFor(PlayerComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		players = engine.getEntitiesFor(Family.getFor(PlayerComponent.class, PositionComponent.class));
		for(int i = 0; i < players.size(); i++) {
			position = positionMapper.get(players.get(i));
			player = playerMapper.get(players.get(i));
			positionBroadcast = new Position();
			positionBroadcast.id = player.id;
			positionBroadcast.x = position.x;
			positionBroadcast.y = position.y;
			server.sendToAllTCP(positionBroadcast);
		}
	}

	@Override
	public void addedToEngine(Engine e) {
		super.addedToEngine(e);
		this.engine = e;
		
		try {
			server = new Server();	
			server.start();
			Network.register(server);
			server.addListener(new ServerListener());
			server.bind(50505);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	class ServerListener extends Listener {
		@Override
		public void received(Connection connection, Object object) {
			super.received(connection, object);
			
			if(object instanceof Connect) {
				handleConnect(connection, (Connect)object);
			}
		}
		
		/**
		 * Init the network connection with a connect Element:
		 * Step 1: Client send Connect with id -1
		 * Step 2: Server send a Connect Object with id set
		 * Step 3: Client send Connect with id back to server
		 * Step 4: Server send send position to client
		 * @param connection
		 * @param con
		 */
		private void handleConnect(Connection connection, Connect con) {
			if(con.id == -1) {
				int id = list.size();
				list.add(connection);
				Color color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1f);
				engine.addEntity(new PlayerEntity(id, color));
			
				//answer
				Connect c = new Connect();
				c.id = id;
				connection.sendTCP(c);
			} else {
				ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.getFor(PlayerComponent.class, PositionComponent.class));
				for(int i = 0; i < entities.size(); i++) {
					player = playerMapper.get(entities.get(i));
					position = positionMapper.get(entities.get(i));
					if(player.id == con.id) {
						Position pos = new Position();
						pos.id = player.id;
						pos.x = (int)(Math.random()*400);
						pos.y = (int)(Math.random()*400);
						pos.color = position.color;
						connection.sendTCP(pos);
					}
				}
			}
		}
	}
}
