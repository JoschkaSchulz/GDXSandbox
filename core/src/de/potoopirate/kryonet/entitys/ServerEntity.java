package de.potoopirate.kryonet.entitys;

import com.badlogic.ashley.core.Entity;

import de.potoopirate.kryonet.components.ServerCompoent;

public class ServerEntity extends Entity {
	public ServerEntity() {
		add(new ServerCompoent());
	}
}
