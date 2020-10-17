package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

	// Indicates if the actor is currently moving
	private boolean isMoving;
	// Indicates how many frames the current move is supposed to take
	private int framesForCurrentMove;
	// The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;
	
    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion() {
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
    }

    /**
     * Move the MoveableAreaEntity
     * @param frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
    protected boolean move(int framesForMove) {
		if (!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
			if (getOwnerArea().leaveAreaCells(this, getLeavingCells()) && getOwnerArea().enterAreaCells(this, getEnteringCells())) {
				framesForCurrentMove = framesForMove;
				if (framesForMove < 1) {
					framesForCurrentMove = 1;
				}
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);				
				isMoving = true;
				return true;
			}
		}
		return false;
	}
    
    /**
	 * Getter for the cells the actor leaves
	 * @return (List<DiscreteCoordinates>): the cells the actor leaves
	 */
	protected final List<DiscreteCoordinates> getLeavingCells() {
		return getCurrentCells();
	}

	/**
	 * Getter for the cells on which the actor enters
	 * @return (List<DiscreteCoordinates>): the cells the actor enters
	 */
	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> enteringCells = new LinkedList<>();
		for (DiscreteCoordinates position : getCurrentCells()) {
			enteringCells.add(position.jump(getOrientation().toVector()));
		}
		return enteringCells;
	}
	
	/**
	 * Getter for the moving state
	 * @return boolean
	 */
	protected boolean getIsMoving() {
		return isMoving;
	}
	
	protected DiscreteCoordinates getTargetMainCellCoordinates() {
		return targetMainCellCoordinates;
	}
	
	@Override
	protected void setOrientation(Orientation orientation) {
		if (!isMoving) {
			super.setOrientation(orientation);
		}
	}

    /// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		if (isMoving) {
			if (!getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
				Vector distance = getOrientation().toVector();
				distance = distance.mul(1.0f / framesForCurrentMove);
				setCurrentPosition(getPosition().add(distance));
			} else {
				resetMotion();
			}
		}
	}

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // TODO implements me #PROJECT #TUTO
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        return null;
    }
}