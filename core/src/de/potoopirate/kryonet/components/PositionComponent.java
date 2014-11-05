package de.potoopirate.kryonet.components;

import com.badlogic.ashley.core.Component;


public class PositionComponent extends Component {
	public int x;
	public int y;
	
	public PositionComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}
}