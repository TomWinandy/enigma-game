package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.HeavyIngot;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Stick;
import ch.epfl.cs107.play.game.enigme.actor.Wizard;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea {
	
	// List of the actors on the Enigme0
	private List<Actor> areaActors;
		
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);

		areaActors = new LinkedList<>();
		
		
		areaActors.add(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN, new DiscreteCoordinates(0, 11), Logic.TRUE, 2));
		areaActors.add(new Potion(this, Orientation.UP, new DiscreteCoordinates(5, 14)));
		areaActors.add(new Potion(this, Orientation.UP, new DiscreteCoordinates(10, 10)));
		areaActors.add(new Potion(this, Orientation.UP, new DiscreteCoordinates(15, 20)));
		areaActors.add(new Wizard(this, Orientation.UP, new DiscreteCoordinates(15, 21)));
		PressurePlate pressurePlate = new PressurePlate(this, new DiscreteCoordinates(7, 11), 2);
		areaActors.add(pressurePlate);
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(10, 14), pressurePlate, 3));
		areaActors.add(new Stick(this, Orientation.DOWN, new DiscreteCoordinates(7, 27)));


		
		for (int i = 5; i < 20; i++) {
			registerActor(new HeavyIngot(this, new DiscreteCoordinates(i, 22)));
		}
		
		
		for (Actor actor : areaActors) {
			registerActor(actor);
		}
		
		registerActor(new Foreground(this));
		
		return true;
	}
	
	@Override 
	public String getTitle() {
		return "Enigme0";
	}
	
	@Override
	public float getCameraScaleFactor() {
		return 30;
	}
	
	@Override
	public void suspend() {
		end();
	}
}