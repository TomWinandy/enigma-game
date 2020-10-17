package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity {
	
	// the name of the area the door is sending to
	private String nameAreaDestination;
	// the coordinates of arrival in the area door is sending to
	private DiscreteCoordinates arrivalCoordinates;
	
	// the coordinates of the cell the door is occupying 
	private List<DiscreteCoordinates> currentCells;
	
	// the sprite that graphically represents the Door
	private Sprite door;
	
	// the sprite scale;
	private float size;
	
	/**
	 * Default Door constructor
     * @param area (Area): Owner area. Not null
     * @param nameAreaDestination (String): the name of the area the door is sending to. Not null
	 * @param arrivalCoordinates (DiscreteCoordinate): the coordinates of arrival in the area door is sending to. Not null
     * @param orientation (Orientation): Initial orientation of the door in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the door in the Area. Not null
	 * @param occupiedCells (List<DiscreteCoordinates>): the coordinates of the cell the door is occupying. Not null
	 * @param size (float): the Door's scale
	 */
	public Door(Area area, String nameAreaDestination, DiscreteCoordinates arrivalCoordinates, Orientation orientation, DiscreteCoordinates position, float size, DiscreteCoordinates... occupiedCells) {
		super(area, orientation, position);
		this.nameAreaDestination = nameAreaDestination;
		this.arrivalCoordinates = arrivalCoordinates;
		setSpriteByName(nameAreaDestination.isEmpty() ? "door.close.1" : "door.open.1");
		currentCells = new LinkedList<>(Arrays.asList(occupiedCells));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				currentCells.add(position.jump(new Vector(i, j)));
			}
		}
		this.size = size;
	}
	
	/**
	 * Another Door constructor
     * @param area (Area): Owner area. Not null
     * @param nameAreaDestination (String): the name of the area the door is sending to. Not null
	 * @param arrivalCoordinates (DiscreteCoordinate): the coordinates of arrival in the area door is sending to. Not null
     * @param orientation (Orientation): Initial orientation of the door in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the door in the Area. Not null
	 */
	public Door(Area area, String nameAreaDestination, DiscreteCoordinates arrivalCoordinates, Orientation orientation, DiscreteCoordinates position) {
		this(area, nameAreaDestination, arrivalCoordinates, orientation, position, 1f);
	}
	
	/**
	 * Getter of the name of the area destination
	 * @return (String): the name of the area destination
	 */
	public String getNameAreaDestination() {
		return nameAreaDestination;
	}
	
	/**
	 * Getter for the coordinates in the area destination
	 * @return (DiscreteCoordinates): the coordinates in the area destination
	 */
	public DiscreteCoordinates getArrivalCoordinates() {
		return arrivalCoordinates;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return currentCells;
	}
	
	/**
	 * Getter for the orientation
	 * @return 
	 */
	public Orientation getExitingOrientation() {
		return getOrientation();
	}
	
	// Change the graphical representation of the door
	protected void setSpriteByName(String spriteName) {
		door = new Sprite(spriteName, size, size, this);
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
		door.draw(canvas);
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
}