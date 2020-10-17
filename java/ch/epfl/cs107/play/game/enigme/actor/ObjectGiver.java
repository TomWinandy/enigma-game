package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class ObjectGiver extends AreaEntity {
	
	// the collectable the object giver is supposed to give 
	private Collectable collectableToGive;

	/**
     * Default ObjectGiver constructor
     * @param area (Area): ObjectGiver area. Not null
     * @param orientation (Orientation): Initial orientation of the ObjectGiver in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the ObjectGiver in the Area. Not null
	 * @param collectableToGive (Collectable): the collectable the object giver is supposed to give 
	 */
	public ObjectGiver(Area area, Orientation orientation, DiscreteCoordinates position, Collectable collectableToGive) {
		super(area, orientation, position);
		this.collectableToGive = collectableToGive;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	/**
	 * Getter for the collectableToGive
	 * @return collectableToGive (Collectable): the collectable the object giver is supposed to give 
	 */
	public Collectable getCollectable() {
		return collectableToGive;
	}
}
