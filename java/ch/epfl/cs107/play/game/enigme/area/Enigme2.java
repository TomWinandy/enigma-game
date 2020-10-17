package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea {
	
	// List of the actors on the Enigme2
	private List<Actor> areaActors;

	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		areaActors = new LinkedList<>();
		
		List<Logic> torches = new LinkedList<Logic>();
		Torch torch1 = new Torch(this, new DiscreteCoordinates(7, 9));
		torches.add(torch1);
		Torch torch2 = new Torch(this, new DiscreteCoordinates(7, 5));
		torches.add(torch2);
		Torch torch3 = new Torch(this, new DiscreteCoordinates(5, 7));
		torches.add(torch3);
		Torch torch4 = new Torch(this, new DiscreteCoordinates(9, 7));
		torches.add(torch4);
		for (Logic torch : torches) {
			areaActors.add((Actor)torch);
		}
		Logic torches1 = new MultipleAnd(torches);
		
		SignalDoor door = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(6, 6), Orientation.DOWN, new DiscreteCoordinates(7, 0), torches1);
		areaActors.add((Actor)door);
		
		for (Actor actor : areaActors) {
			registerActor(actor);
		}
		
		registerActor(new Foreground(this));
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Enigme2";
	}
}