package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class HeavyIngot extends Collectable implements Interactor {
	
	// the sprite that graphically represents the HeavyIngot
	private Sprite ingot;
	
	// the handler of the HeavyIngot
	private HeavyIngotHandler handler;
	
	/**
	 * HeavyIngot Constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the Key in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Key in the Area. Not null
	 */
    public HeavyIngot(Area area, Orientation orientation, DiscreteCoordinates position) {
    	super(area, orientation, position);
    	ingot = new Sprite("ingot.1", 1, 1.f, this);
    	handler = new HeavyIngotHandler();
    }
    
    /**
	 * Default HeavyIngot Constructor: orientation is set at Orientation.DOWN
	 * @param area (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the Key in the Area. Not null
	 */
    public HeavyIngot(Area area, DiscreteCoordinates position) {
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
		ingot.draw(canvas);
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}
	
	@Override
	String getSpriteName() {
		return ingot.getName();
	}
	
	
private class HeavyIngotHandler implements EnigmeInteractionVisitor {
		
		@Override
		public void interactWith(Activable activable) {
				activable.active();
		}
	}
	
}