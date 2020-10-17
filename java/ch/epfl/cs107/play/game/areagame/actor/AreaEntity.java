package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {

    // An AreaEntity knows its own Area
	private Area ownerArea;
	// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	// Coordinates of the main Cell linked to the entity
	private DiscreteCoordinates currentMainCellCoordinates;

    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(position.toVector());
        currentMainCellCoordinates = position;
        ownerArea = area;
        this.orientation = orientation;
    }


    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates): the coordinates of the main cell occupied by the AreaEntity
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        return currentMainCellCoordinates;
    }
    
    /**
     * Getter for the orientation of the AreaEntity
     * @return (Orientation): the orientation of the AreaEntity
     */
    protected Orientation getOrientation() {
    	return orientation;
    }
    
    /**
     * Getter for the number of the column corresponding to the right Orientation in the Sprite given
     * @return (int)
     */
    protected int getOrientationToInt() {
    	if (orientation.equals(Orientation.DOWN)) {
			return 0;
		} else if (orientation.equals(Orientation.LEFT)) {
			return 1;
		} else if (orientation.equals(Orientation.UP)) {
			return 2;
		} else if (orientation.equals(Orientation.RIGHT)) {
			return 3;
		} else {
			return -1;
		}
    }
    
    /**
     * Getter for the entity's Area
     * @return (Area): the entity's Area
     */
    protected Area getOwnerArea() {
    	return ownerArea;
    }

    
    @Override
    protected void setCurrentPosition(Vector v) {
    	if (DiscreteCoordinates.isCoordinates(v)) {
    		super.setCurrentPosition(v.round());
    		currentMainCellCoordinates = new DiscreteCoordinates((int) v.round().getX(), (int) v.round().getY()); 
    	} else {
    		super.setCurrentPosition(v);
    	}
    }
    
    /**
     * Update coordinates of the current position (i.e. after motion)
     * after position change, the transform need to be updated to. Hence set it to null
     * @param coordinates (DiscreteCoordinates): The new Position. Not null
     */
    protected void setCurrentPosition(DiscreteCoordinates coordinates) {
    	setCurrentPosition(coordinates.toVector());
    }
    
    /**
     * Setter for the orientation of the AreaEntity
     * @param orientation (Orientation): the orientation of the AreaEntity to be set
     */
    protected void setOrientation(Orientation orientation) {
    	this.orientation = orientation;
    }
    
    /**
     * Setter for the entity's Area
     * @param area (Area): the entity's Area to be set
     */
    protected void setOwnerArea(Area area) {
    	ownerArea = area;
    }
}