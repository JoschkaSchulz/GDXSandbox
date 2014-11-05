package de.potoopirate.ashley.component;

import com.badlogic.ashley.core.Component;

public class PlayerComponent extends Component {
	public static final int STATE_GAME = 1;
	public static final int STATE_GAME_OVER = 2;
	
	public int currentState = STATE_GAME;
	public int points;
}
