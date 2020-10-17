package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some
 * Interactable)
 * 
 * @see Interactable This interface makes sense only in the "Area Context" with
 *      Actor contained into Area Cell
 */
public interface Interactor {

	/**
	 * Give the list of the cells the interactor is on
	 * 
	 * @return (List<DiscreteCoordinates>): the list of the cells the interactor is
	 *         on
	 */
	List<DiscreteCoordinates> getCurrentCells();

	/**
	 * Give the list of the cells that are in the field of view of the interactor
	 * 
	 * @return (List<DiscreteCoordinates>): the list of the cells in the field of
	 *         view of the interactor
	 */
	List<DiscreteCoordinates> getFieldOfViewCells();

	/**
	 * Tell if the Interactor wants contact interaction
	 * @return (boolean): true if the Interactor wants contact interaction
	 */
	boolean wantsCellInteraction();

	/**
	 * Tell if the Interactor wants distance interaction
	 * @return (boolean): true if the Interactor wants distance interaction
	 */
	boolean wantsViewInteraction();

	/**
	 * handle the interaction between an interactor and an interactable 
	 * @param other (Interactable): the interactable
	 */
	void interactWith(Interactable other);
}