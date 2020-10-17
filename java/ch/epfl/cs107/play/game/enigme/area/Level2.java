package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		Door door = new Door(this, "LevelSelector", new DiscreteCoordinates(2, 6), Orientation.DOWN, new DiscreteCoordinates(5, 0));
		Apple apple = new Apple(this, Orientation.UP, new DiscreteCoordinates(5, 6));


		registerActor(door);
		registerActor(apple);
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Level2";
	}
}