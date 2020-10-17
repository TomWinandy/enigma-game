package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends Switchable {
	
	// the sprite that graphically represents the PressureSwitch
	private Sprite pressureSwitch;
		
	/**
	 * PressureSwitch Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the PressureSwitch in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the PressureSwitch in the Area. Not null
	 */
    public PressureSwitch(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	toOff();
    }
    
    /**
	 * Default PressureSwitch Constructor: orientation is set at Orientation.DOWN
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the PressureSwitch in the Area. Not null
	 */
    public PressureSwitch(Area area, DiscreteCoordinates position) {
    	this(area, Orientation.DOWN, position);
	}
    
    /**
     * Assign the graphical representation of a PressureSwitch that is switched on
     */
    public void toOn() {
		pressureSwitch = new Sprite("GroundLightOn", 1, 1.f, this);
    }
    
    /**
     * Assign the graphical representation of a PressureSwitch that is switched off
     */
    public void toOff() {
    	pressureSwitch = new Sprite("GroundLightOff", 1, 1.f, this);
    }

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		pressureSwitch.draw(canvas);
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
}