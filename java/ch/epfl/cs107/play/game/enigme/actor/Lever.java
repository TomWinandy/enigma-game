package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends Switchable {
	
	// the sprite that graphically represents the Lever
	private Sprite lever;
		
	/**
	 * Lever Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Lever in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Lever in the Area. Not null
	 */
    public Lever(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	toOff();
    }
    
    /**
	 * Default Lever Constructor: orientation is set at Orientation.DOWN
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Lever in the Area. Not null
	 */
    public Lever(Area area, DiscreteCoordinates position) {
    	this(area, Orientation.DOWN, position);
	}
    
    /**
     * Assign the graphical representation of a activated lever to lever
     */
    public void toOn() {
		lever = new Sprite("lever.big.left", 1, 1.f, this);
    }
    
    /**
     * Assign the graphical representation of a desactivated lever to lever
     */
    public void toOff() {
    	lever = new Sprite("lever.big.right", 1, 1.f, this);
    }

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		lever.draw(canvas);
	}
}