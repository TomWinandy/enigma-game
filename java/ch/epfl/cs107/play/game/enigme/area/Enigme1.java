package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.HeavyIngot;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Stick;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.game.enigme.actor.Wizard;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea {
	
	// List of the actors on the Enigme1
	private List<Actor> areaActors;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		
		areaActors = new LinkedList<>();
		
		Key key = new Key(this, new DiscreteCoordinates(23, 31));
		areaActors.add(key);
		
		areaActors.add(new SignalDoor(this, "Enigme2", new DiscreteCoordinates(7, 1), Orientation.DOWN, new DiscreteCoordinates(6, 32), key));

		
		// Actors for the first enigme
		
		areaActors.add(new Wizard(this, Orientation.DOWN, new DiscreteCoordinates(8, 28)));
		
		PressurePlate condition1A = new PressurePlate(this, new DiscreteCoordinates(6, 25), 1f);
		areaActors.add(condition1A);
		
		List<Logic> reopen1 = new LinkedList<Logic>();
		reopen1.add(new PressurePlate(this, new DiscreteCoordinates(6, 16), 1));
		reopen1.add(new PressurePlate(this, new DiscreteCoordinates(7, 16), 1));
		for (Logic actorLogic : reopen1) {
			areaActors.add((Actor) actorLogic);
		}
		Logic condition1B = new MultipleAnd(reopen1);
		
		Logic open1 = new Or(condition1A, condition1B);
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6, 17), open1, 2));		
		
		
		// Actors for the second enigme
				
		List<Logic> entities2A = new LinkedList<Logic>();
		entities2A.add(new Lever(this, new DiscreteCoordinates(1, 16)));
		entities2A.add(new Lever(this, new DiscreteCoordinates(12, 16)));
		entities2A.add(new Lever(this, new DiscreteCoordinates(1, 11)));
		entities2A.add(new Lever(this, new DiscreteCoordinates(12, 11)));
		entities2A.add(new PressurePlate(this, new DiscreteCoordinates(8, 11)));
		for (Logic actorLogic : entities2A) {
			areaActors.add((Actor) actorLogic);
		}
		Logic condition2A = new MultipleAnd(entities2A);
		
		List<Logic> reopen2 = new LinkedList<Logic>();
		reopen2.add(new PressurePlate(this, new DiscreteCoordinates(6, 8)));
		reopen2.add(new PressurePlate(this, new DiscreteCoordinates(7, 8)));
		for (Logic actorLogic : reopen2) {
			areaActors.add((Actor) actorLogic);
		}
		Logic condition2B = new MultipleAnd(reopen2);
		
		Logic open2 = new Or(condition2A, condition2B);
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6, 9), open2, 2));
		
		
		// Actors for the third enigme
		
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(2, 2), Logic.FALSE));
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(1, 2), Logic.FALSE));
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(2, 1), Logic.FALSE));
		
		areaActors.add(new HeavyIngot(this, new DiscreteCoordinates(1, 1)));
		
		List<Logic> entities3A = new LinkedList<Logic>();
		entities3A.add(new Torch(this, new DiscreteCoordinates(12, 6)));
		entities3A.add(new Torch(this, new DiscreteCoordinates(12, 4)));
		for (Logic actorLogic : entities3A) {
			areaActors.add((Actor) actorLogic);
		}
		Logic open3 = new MultipleAnd(entities3A);

		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(13, 5), open3));
		
				
		// Actors for the fourth enigme
	
		areaActors.add(new Stick(this, Orientation.DOWN, new DiscreteCoordinates(15, 0)));
		areaActors.add(new HeavyIngot(this, new DiscreteCoordinates(22, 1)));

		List<Logic> entities4A = new LinkedList<Logic>();
		entities4A.add(new PressurePlate(this, new DiscreteCoordinates(14, 7)));
		entities4A.add(new PressurePlate(this, new DiscreteCoordinates(23, 7)));
		for (Logic actorLogic : entities4A) {
			areaActors.add((Actor) actorLogic);
		}
		Logic condition4A = new MultipleAnd(entities4A);

		PressurePlate condition4B = new PressurePlate(this, new DiscreteCoordinates(22, 14));
		areaActors.add(condition4B);
		
		Logic open4 = new Or(condition4A, condition4B);
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(23, 14), open4));

		
		// Actor for the fifth enigme
		
		areaActors.add(new Apple(this, Orientation.UP, new DiscreteCoordinates(10, 22)));
		areaActors.add(new HeavyIngot(this, new DiscreteCoordinates(18, 22)));

		List<Logic> open5 = new LinkedList<>();
		open5.add(new PressurePlate(this, new DiscreteCoordinates(19, 37)));
		open5.add(new PressurePlate(this, new DiscreteCoordinates(17, 18)));
		open5.add(new PressurePlate(this, new DiscreteCoordinates(10, 25)));
		for (Logic actorLogic : open5) {
			areaActors.add((Actor) actorLogic);
		}
		
		Logic condition5 = new MultipleAnd(open5);
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(20, 31), condition5));		
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(23, 32), Logic.FALSE));		
		
		
		for (Actor actor : areaActors) {
			registerActor(actor);
		}
		
		registerActor(new Foreground(this));

		return true;
	}
	
	@Override
	public String getTitle() {
		return "Enigme1";
	}
	
	@Override
	public float getCameraScaleFactor() {
		return 20;
	}
	
}