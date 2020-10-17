package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Usable extends Collectable {
	
	// boolean that remembers whether the Usable has been used or not
	private boolean used;

	/**
     * Default Usable constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Usable in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Usable in the Area. Not null
     */
	protected Usable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		resetUsed();
	}
	
	/**
	 * Set used to true
	 */
	protected void use() {
		used = true;
	}
	
	/**
	 * Getter for the used state
	 * @return
	 */
	public boolean getUsed() {
		return used;
	}
	
	/**
	 * Set used to false
	 */
	public void resetUsed() {
		used = false;
	}
}
