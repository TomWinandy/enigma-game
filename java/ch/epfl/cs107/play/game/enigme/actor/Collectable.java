package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public abstract class Collectable extends AreaEntity implements Logic {
	
	// tell if the Collectable is collected or not
	private boolean collected;
	
	/**
     * Default Collectable constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Collectable in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Collectable in the Area. Not null
     */
	protected Collectable(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	collected = false;
	}
	
	/**
	 * Set collected to true
	 */
	public void collect() {
		collected = true;
		getOwnerArea().unregisterActor(this);
		
	}
	
	/**
	 * Set collected to false
	 */
	public void pose(Area areaToEnter, DiscreteCoordinates coordinates) {
		collected = false;
		setOwnerArea(areaToEnter);
		getOwnerArea().registerActor(this);
		setCurrentPosition(coordinates);
	}
	
	/**
	 * Getter for collected
	 * @return (boolean): true if the Collectable is collected
	 */
	protected boolean getCollected() {
		return collected;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	@Override
	public boolean isOn() {
		return collected;
	}
	
    /** @return (String): image name, may be null */
	abstract String getSpriteName();
}