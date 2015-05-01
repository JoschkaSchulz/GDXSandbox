package de.potoopirate.ashley.component;

import java.util.ArrayList;

import com.badlogic.ashley.core.Component;

public class HighscoreComponent extends Component {
	public ArrayList<Integer> pointList;
	public int points;
	
	public HighscoreComponent() {
		pointList = new ArrayList<Integer>();
		points = 0;
	}
}
