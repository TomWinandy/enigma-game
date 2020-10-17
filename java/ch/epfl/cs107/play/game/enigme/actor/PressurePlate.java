package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends Activable {
	
	// the sprite that graphically represents the PressurePlate
	private Sprite pressurePlate;
	private Sprite pressureLight;
			
	/**
	 * PressurePlate Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the PressurePlate in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the PressurePlate in the Area. Not null
	 */
    public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position, float activationTime) {
    	super(area, orientation, position, activationTime);
    	pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
    	toOff();
    }
    
    /**
	 * Auxiliary PressurePlate Constructor
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the PressurePlate in the Area. Not null
	 */
    public PressurePlate(Area area, DiscreteCoordinates position, float activationTime) {
    	this(area, Orientation.DOWN, position, activationTime);
	}
    
    /**
     * Auxiliary PressurePlate Constructor
     * @param area
     * @param position
     */
    public PressurePlate(Area area, DiscreteCoordinates position) {
    	this(area, Orientation.DOWN, position, 1f);
	}

    
    /**
     * Assign the graphical representation of a activated PressurePlate to PressurePlate
     */
    public void toOn() {
		pressureLight = new Sprite("GroundLightOn", 1, 1.f, this);
    }
    
    /**
     * Assign the graphical representation of a desactivated PressurePlate to PressurePlate
     */
    public void toOff() {
    	pressureLight = new Sprite("GroundLightOff", 1, 1.f, this);
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
		pressurePlate.draw(canvas);
		pressureLight.draw(canvas);
	}
}