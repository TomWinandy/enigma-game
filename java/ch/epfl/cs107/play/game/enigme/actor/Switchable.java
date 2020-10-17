package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public abstract class Switchable extends AreaEntity implements Logic {

	// tell if the Switchable is activated or not
	private boolean on;
	
	/**
	 * Default Switchable Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Switchable in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Switchable in the Area. Not null
	 */
	public Switchable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		on = false;
	}
	
	/**
	 * Getter of on 
	 * @return (boolean): true if the Switchable is activated
	 */
	public boolean isOn() {
		return on;
	}
	
	/**
	 * Swap the value of on: true -> false or false -> true. 
	 * Then it makes the necessary changes to Switchable
	 */
	public void swap() {
		on = !on;
		if (on) {
			toOn();
		} else {
			toOff();
		}
	}
	
	/**
	 * Assign the necessary value to Switchable when it is activated
	 */
	abstract void toOn();
	
	/**
	 * Assign the necessary value to Switchable when it is desactivated
	 */
	abstract void toOff();
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
}