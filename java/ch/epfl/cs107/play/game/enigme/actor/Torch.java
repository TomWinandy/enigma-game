package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Torch extends Switchable implements Logic {
	
	// the sprite that graphically represents the Torch
	private Sprite torch;
	
	// the state of the movement of the lighted torch
	private boolean movingState;
	
	// it tells if the torch is lighted up
	private boolean isLightedUp;
		
	/**
	 * Torch Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Torch in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Torch in the Area. Not null
	 */
    public Torch(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	toOff();
    }
    
    /**
	 * Default Torch Constructor: orientation is set at Orientation.DOWN
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Torch in the Area. Not null
	 */
    public Torch(Area area, DiscreteCoordinates position) {
    	this(area, Orientation.DOWN, position);
	}
    
    /**
     * Assign the graphical representation of a lighted torch to torch 
     */
    public void toOn() {
		isLightedUp = true;
    }
    
    /**
     * Assign the graphical representation of a unlighted torch to torch 
     */
    public void toOff() {
    	torch = new Sprite("torch.ground.off", 1, 1.f, this);
		isLightedUp = false;
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
	
	/**
	 * It tells the state of the movement of the lighted torch
	 * @return movingState (boolean): the state of the movement of the lighted torch
	 */
	public boolean move() {
		movingState = !movingState;
		return movingState;
	}

	@Override
	public void draw(Canvas canvas) {
		if(isLightedUp) {
			if(move()) {
				torch = new Sprite("torch.ground.on.1", 1, 1.f, this);
			} else {
				torch = new Sprite("torch.ground.on.2", 1, 1.f, this);
			}
		}
		torch.draw(canvas);
	}
}