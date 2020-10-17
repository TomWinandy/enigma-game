package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public abstract class Activable extends AreaEntity implements Logic {
	
	// tell if the Activable is activated or not
	private boolean on;
	// the time an activable stays activated after it has been activated 
	private float defaultActivationTime;
	// the time since an activable has been activated 
	private float activeSince;
	
	/**
	 * Default Activable Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Activable in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Activable in the Area. Not null
	 * @param defaultActivationTime (float): default activation time of the Activable
	 */
	public Activable(Area area, Orientation orientation, DiscreteCoordinates position, float defaultActivationTime) {
		super(area, orientation, position);
		this.defaultActivationTime = defaultActivationTime;
		on = false;
	}
	
	@Override 
	public boolean isOn() {
		return on;
	}
	
	/**
	 * Activate an Activable
	 */
	public void active() {
		toOn();
		on = true;
		activeSince = 0;
	}
	
	/**
	 * Desactivate an Activable
	 */
	private void desactive() {
		on = false;
		toOff();
	}
	
	/**
	 * Assign the necessary value to Activable when it is activated
	 */
	abstract void toOn();
	
	/**
	 * Assign the necessary value to Activable when it is activated
	 */
	abstract void toOff();
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	@Override
	public void update(float deltaTime) {
		if (isOn() && (activeSince > defaultActivationTime)) {
			desactive();
		}
		activeSince += deltaTime;
		super.update(deltaTime);
	}
}