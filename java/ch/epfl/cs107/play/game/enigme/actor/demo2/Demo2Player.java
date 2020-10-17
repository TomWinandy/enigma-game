package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity {
	// Animation duration in frame number
	private final static int ANIMATION_DURATION = 3;

	// Tell if Demo2Player is crossingADoor
	private boolean crossingADoor;
	// The Sprite of the Demo2Player
	private Sprite ghost;
	
	/**
     * Default Demo2Player constructor
     * @param area (Area): Owner area. Not null
     * @param coordinates (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		enterArea(area, coordinates);
		setCrossingADoor(false);
		ghost = new Sprite("ghost.1", 1, 1.f, this);
	}
	
	/**
	 * Another Demo2Player constructor
	 * @param area (Area): Owner area. Not null
     * @param coordinates (DiscreteCoordinates): Initial position of the entity. Not null
	 */
	public Demo2Player(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
	}
	
	/**
	 * Register current entity in a new Area and set its position as given
	 * @param area (Area): the area to be entered
	 * @param position (DiscreteCoordinates): the position of the entity in the new Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		area.registerActor(this);
		setCurrentPosition(position.toVector());
		resetMotion();
	}
	
	/**
	 * Unregister current entity from its Area
	 */
	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
		setOwnerArea(null);
	}
	
	/**
	 * Getter for the crossingADoor attribute
	 * @return (boolean): true if the entity is crossing a Door
	 */
	public boolean getCrossingADoor() {
		return crossingADoor;
	}
	
	/**
	 * Setter for the crossingADoor attribute
	 * @param (boolean): the state of crossingADoor to be set
	 */
	public void setCrossingADoor(boolean state) {
		crossingADoor = state;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		Keyboard keyboard = getOwnerArea().getKeyboard();
		
		Button leftArrown = keyboard.get(Keyboard.LEFT);
		if (leftArrown.isDown()) {
			if (getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.LEFT);
			}
		} 
		
		Button rightArrow = keyboard.get(Keyboard.RIGHT);	
		if (rightArrow.isDown()) {
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.RIGHT);
			}
		} 
		
		Button downArrow = keyboard.get(Keyboard.DOWN);	
		if (downArrow.isDown()) {
			if (getOrientation().equals(Orientation.DOWN)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.DOWN);
			}
		} 
		
		Button upArrow = keyboard.get(Keyboard.UP);	
		if (upArrow.isDown()) {
			if (getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.UP);
			}
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		ghost.draw(canvas);
	}
	
	@Override
	protected boolean move(int framesForMove) {
		for (DiscreteCoordinates coordinates : getEnteringCells()) {
			if (((Demo2Cell)(getOwnerArea().getAreaBehavior().getCellAt(coordinates))).getNature().equals(Demo2CellType.DOOR)) {
				setCrossingADoor(true);
			}
		}
		return super.move(framesForMove);
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
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// No utility but needed because of the implementation of Part 3
	}
}