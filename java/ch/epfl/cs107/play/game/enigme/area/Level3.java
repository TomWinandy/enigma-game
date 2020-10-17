package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {
	
	// List of the actors on the Level3
	private List<Actor> areaActors;
		
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);

		areaActors = new LinkedList<>();
		
		Key key = new Key(this, new DiscreteCoordinates(1, 3));
		areaActors.add(key);
		Torch torch = new Torch(this, new DiscreteCoordinates(7, 5));
		areaActors.add(torch);

		List<Logic> pressureSwitches = new LinkedList<Logic>();
		PressureSwitch pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(4, 4));
		pressureSwitches.add(pressureSwitch1);
		PressureSwitch pressureSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(5, 4));
		pressureSwitches.add(pressureSwitch2);
		PressureSwitch pressureSwitch3 = new PressureSwitch(this, new DiscreteCoordinates(6, 4));
		pressureSwitches.add(pressureSwitch3);
		PressureSwitch pressureSwitch4 = new PressureSwitch(this, new DiscreteCoordinates(5, 5));
		pressureSwitches.add(pressureSwitch4);
		PressureSwitch pressureSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(4, 6));
		pressureSwitches.add(pressureSwitch5);
		PressureSwitch pressureSwitch6 = new PressureSwitch(this, new DiscreteCoordinates(5, 6));
		pressureSwitches.add(pressureSwitch6);
		PressureSwitch pressureSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(6, 6));
		pressureSwitches.add(pressureSwitch7);
		
		for (Logic pressureSwitch : pressureSwitches) {
			areaActors.add((Actor)pressureSwitch);
		}
		
		List<Logic> levers = new LinkedList<Logic>();
		Lever lever1 = new Lever(this, new DiscreteCoordinates(10, 5));
		levers.add(lever1);
		Lever lever2 = new Lever(this, new DiscreteCoordinates(9, 5));
		levers.add(lever2);
		Lever lever3 = new Lever(this, new DiscreteCoordinates(8, 5));
		levers.add(lever3);
		
		for (Logic lever : levers) {
			areaActors.add((Actor)lever);
		}
		
		PressurePlate pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9, 8), 10f);
		areaActors.add(pressurePlate);

		areaActors.add(new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.DOWN, new DiscreteCoordinates(5, 9), key));

		Logic rockPressureSwitches = new MultipleAnd(pressureSwitches);
		Logic rockLeversOrTorch = new Or(new LogicNumber(5, levers), torch);
		
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(6, 8), pressurePlate));
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(5, 8), rockPressureSwitches));
		areaActors.add(new SignalRock(this, Orientation.UP, new DiscreteCoordinates(4, 8), rockLeversOrTorch));
		
		for (Actor actor : areaActors) {
			registerActor(actor);
		}
		
		return true;
	}
	
	@Override 
	public String getTitle() {
		return "Level3";
	}
}