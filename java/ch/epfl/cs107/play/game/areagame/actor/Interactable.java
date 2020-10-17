package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with
 * them)
 * 
 * @see Interactor This interface makes sense only in the "AreaGame" context
 *      with Actor contained into Area Cell
 */
public interface Interactable {

	/**
	 * Give the list of the cells the interactable is on
	 * 
	 * @return (List<DiscreteCoordinates>): the list of the cells the interactable
	 *         is on
	 */
	List<DiscreteCoordinates> getCurrentCells();

	/**
	 * Indicate if the Interactable can be crossed
	 * 
	 * @return (boolean): true if the Interactable cannot be crossed
	 */
	boolean takeCellSpace();

	/**
	 * Indicate if the Interactable accept distant interactions
	 * 
	 * @return (boolean): true if the Interactable accept distant interactions
	 */
	boolean isViewInteractable();

	/**
	 * Indicate if the Interactable accept contact interactions
	 * 
	 * @return (boolean): true if the Interactable accept contact interactions
	 */
	boolean isCellInteractable();

	/**
	 * tell on which condition the interactor accept the interaction
	 * @param v (AreaInteractionVisitor): the interactor's handler
	 */
	default public void acceptInteraction(AreaInteractionVisitor v) {
			v.interactWith(this);
	}
}