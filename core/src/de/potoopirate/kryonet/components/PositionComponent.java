package de.potoopirate.kryonet.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;


public class PositionComponent extends Component {
	public int x;
	public int y;
	public Color color;
	
	public PositionComponent(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
