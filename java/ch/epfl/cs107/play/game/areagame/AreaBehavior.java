package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	/// We will convert the image into an array of cells
	private final Cell[][] cells;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
	public AreaBehavior(Window window, String fileName) {
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}
	
	/**
	 * Check if an entity can leave the cells in coordinates
	 * @param (Interactable) entity: the entity that is leaving
	 * @param (List<DiscreteCoordinates>) coordinates: the coordinates of the cell that are left
	 * @return (boolean): true if the entity can leave the cells in coordinates
	 */
	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates position : coordinates) {
			if (!getCellAt(position).canLeave(entity)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if an entity can enter the cells in coordinates
	 * @param (Interactable) entity: the entity that is entering
	 * @param (List<DiscreteCoordinates>) coordinates: the coordinates of the cell that are entered
	 * @return (boolean): true if the entity can enter the cells in coordinates
	 */
	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates position : coordinates) {
			if (!isInGrid(position) || !getCellAt(position).canEnter(entity)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Remove entity from all cells in coordinates
	 * @param (Interactable) entity: the entity that is leaving
	 * @param (List<DiscreteCoordinates>) coordinates: the coordinates of the cell that are left
	 */
	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates position : coordinates) {
			getCellAt(position).leave(entity);
		}
	}

	/**
	 * Add entity to all cells in coordinates
	 * @param (Interactable) entity: the entity that is entering
	 * @param (List<DiscreteCoordinates>) coordinates: the coordinates of the cell that are entered
	 */
	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates position : coordinates) {
			getCellAt(position).enter(entity);
		}
	}
	
	/**
	 * Manage the contact interaction between an Interactor and the Interactables at the position occupied by the Interactor
	 * @param interactor (Interactor): the Interactor that interact
	 */
	public void cellInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates position : interactor.getCurrentCells()) {
			getCellAt(position).cellInteractionOf(interactor);
		}
	}
	
	/**
	 * Manage the distance interaction between an Interactor and the Interactables in the view field of the Interactor
	 * @param interactor (Interactor): the Interactor that interact
	 */
	public void viewInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates position : interactor.getFieldOfViewCells()) {
			if (isInGrid(position)) {
				getCellAt(position).viewInteractionOf(interactor);
			}
		}
	}
	
	/**
	 * Determine if position is in the grid
	 * @param (DiscreteCoordinates) position: the position that we wonder if it is in the grid 
	 * @return (boolean): true if position is contained in the grid
	 */
	public boolean isInGrid(DiscreteCoordinates position) {
		if (position.x < 0 || position.x >= width) {
			return false;
		} else if (position.y < 0 || position.y >= height) {
			return false;
		} else {
			return true;
		}
	}

	
	/**
	 * Getter for the area width
	 * @return (int) : the width in number of cols
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter for the area height
	 * @return (int) : the height in number of rows
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Getter for the behavior map
	 * @return Image : the behavior map
	 */
	public Image getBehaviorMap() {
		return behaviorMap;
	}
	
	/**
	 * Getter for the cell located at position
	 * @param (DiscreteCoordinates) position: the position of the Cell we want to get
	 * @return (Cell): the Cell that have the coordinates position
	 */
	public Cell getCellAt(DiscreteCoordinates position) {
		return cells[position.x][position.y];
	}
	
	/**
	 * Setter used for each cell of the grid cells
	 * @param x : row of the grid
	 * @param y : column of the grid
	 * @param cell : type of the cell to add
	 */
	protected void setCell(int x, int y, Cell cell) {
		cells[x][y] = cell;
	}


    /**
	 * Each game will have its own Cell extension .
	 */
    public abstract class Cell implements Interactable {
    	
    	// coordinates of the Cell on the grid
    	private DiscreteCoordinates coordinates;
    	// list of the Interactables in the Cell
		private Set<Interactable> entities;

		/**
		 * Default Cell Constructor
		 * @param (int) x: the x coordinate of the cell
		 * @param (int) y: the y coordinate of the cell
		 */
		public Cell(int x, int y) {
			coordinates = new DiscreteCoordinates(x, y);
			entities = new HashSet<>();
		}
		
		@Override
		public List<DiscreteCoordinates> getCurrentCells() {
			List<DiscreteCoordinates> currentCells = new LinkedList<>();
			currentCells.add(coordinates);
			return currentCells;
		}
		
		/**
		 * Add an Interactable to the cell content
		 * @param entity (Interactable): the Interactable that enter the cell
		 */
		private void enter(Interactable entity) {
			entities.add(entity);
		}
		
		/**
		 * Remove an Interactable from the cell content
		 * @param entity (Interactable): the Interactable that leave the cell
		 */
		private void leave(Interactable entity) {
			entities.remove(entity);
		}
		
		/**
		 * Check if an entity is allowed to be added to the Cell content
		 * @param entity (Interactable): the Interactable that enter the cell
		 * @return true if entity can be added to content, and false otherwise
		 */
		protected abstract boolean canEnter(Interactable entity);
		
		/**
		 * Check if an entity is allowed to be removed to the Cell content
		 * @param entity (Interactable): the Interactable that leave the cell
		 * @return true if entity can be removed from content, and false otherwise
		 */
		protected abstract boolean canLeave(Interactable entity);
		
		/**
		 * Manage the contact interaction between an Interactor and the Interactable at the position of the Cell
		 * @param interactor (Interactor): the Interactor that interact
		 */
		private void cellInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isCellInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}
		
		/**
		 * Manage the distance interaction between an Interactor and the Interactable at the position of the Cell
		 * @param interactor (Interactor): the Interactor that interact
		 */
		private void viewInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isViewInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}
		
		/**
		 * Getter for the entities set contained in the cell
		 * @return Set<Interactable>
		 */
		protected Set<Interactable> getEntities() {
			return entities;
		}
	}
}