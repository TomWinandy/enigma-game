package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable {
	
	// the sprite that graphically represents the Apple
	private Sprite apple;
		
	/**
     * Default Apple constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Apple in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Apple in the Area. Not null
     */
    public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	apple = new Sprite("apple.1", 1, 1.f, this);
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
		apple.draw(canvas);
	}

	@Override
	String getSpriteName() {
		return apple.getName();
	}
}