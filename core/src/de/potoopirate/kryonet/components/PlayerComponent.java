package de.potoopirate.kryonet.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent extends Component {
	public int id;
	public String name;
	
	public PlayerComponent(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
