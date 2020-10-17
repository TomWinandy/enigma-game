package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door {
	
	// the signal that determines if the SignalDoor is open or not 
	private Logic logic;
	
	/**
	 * 
	 * Default SignalDoor constructor
     * @param area (Area): Owner area. Not null
     * @param nameAreaDestination (String): the name of the area the signaldoor is sending to. Not null
	 * @param arrivalCoordinates (DiscreteCoordinate): the coordinates of arrival in the area signaldoor is sending to. Not null
     * @param orientation (Orientation): Initial orientation of the signaldoor in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the signaldoor in the Area. Not null
	 * @param logic (Logic): the signal that determines if the SignalRock appears or not 
	 */
	public SignalDoor(Area area, String nameAreaDestination, DiscreteCoordinates arrivalCoordinates, Orientation orientation, DiscreteCoordinates position, Logic logic) {
		this(area, nameAreaDestination, arrivalCoordinates, orientation, position, logic, 1);
	}
	
	/**
	 * 
	  * Another SignalDoor constructor
     * @param area (Area): Owner area. Not null
     * @param nameAreaDestination (String): the name of the area the signaldoor is sending to. Not null
	 * @param arrivalCoordinates (DiscreteCoordinate): the coordinates of arrival in the area signaldoor is sending to. Not null
     * @param orientation (Orientation): Initial orientation of the signaldoor in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the signaldoor in the Area. Not null
	 * @param logic (Logic): the signal that determines if the SignalRock appears or not 
	 * @param size (float): size of the sprite of the SignalDoor
	 */
	public SignalDoor(Area area, String nameAreaDestination, DiscreteCoordinates arrivalCoordinates, Orientation orientation, DiscreteCoordinates position, Logic logic, float size) {
		super(area, nameAreaDestination, arrivalCoordinates, orientation, position, size);
		this.logic = logic;
		setSpriteByName();
	}
	
	/**
	 * It determines the sprite that must be used according to the state of logic
	 */
	private void setSpriteByName() {
		super.setSpriteByName(logic.isOn() ? "door.open.1" : "door.close.1");
	}
	
	@Override
	public boolean takeCellSpace() {
		return !logic.isOn();
	}
	
	@Override
	public void draw(Canvas canvas) {
		setSpriteByName();
		super.draw(canvas);
	}
}