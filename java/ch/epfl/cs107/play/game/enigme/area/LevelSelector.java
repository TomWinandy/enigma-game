package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {
	@Override 
	public String getTitle() {
		return "LevelSelector";
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		Door door1 = new SignalDoor(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(1, 7), Logic.TRUE);
		Door door2 = new SignalDoor(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(2, 7), Logic.TRUE);
		Door door3 = new SignalDoor(this, "Level3", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(3, 7), Logic.TRUE);
		Door door4 = new SignalDoor(this, "Enigme0", new DiscreteCoordinates(2, 11), Orientation.RIGHT, new DiscreteCoordinates(4, 7), Logic.TRUE);
		Door door5 = new SignalDoor(this, "Enigme1", new DiscreteCoordinates(6, 31), Orientation.DOWN, new DiscreteCoordinates(5, 7), Logic.TRUE);
		Door door6 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(6, 7), Logic.FALSE);
		Door door7 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(7, 7), Logic.FALSE);
		Door door8 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(8, 7), Logic.FALSE);

		registerActor(door1);
		registerActor(door2);
		registerActor(door3);
		registerActor(door4);
		registerActor(door5);
		registerActor(door6);
		registerActor(door7);
		registerActor(door8);
		
		return true;
	}
}