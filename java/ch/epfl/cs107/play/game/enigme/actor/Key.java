package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Collectable {
	
	// the sprite that graphically represents the Key
	private Sprite key;
	
	/**
	 * Key Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Key in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Key in the Area. Not null
	 */
    public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	key = new Sprite("key.1", 1, 1.f, this);
    }
    
    /**
	 * Default Key Constructor: orientation is set at Orientation.DOWN
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Key in the Area. Not null
	 */
    public Key(Area area, DiscreteCoordinates position) {
    	this(area, Orientation.DOWN, position);
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
		key.draw(canvas);
	}
	
	@Override
	String getSpriteName() {
		return key.getName();
	}
}