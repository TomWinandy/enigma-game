package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {
	
	/**
     * Default EnigmeBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
	public EnigmeBehavior(Window window, String fileName) {
		super(window, fileName);
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				EnigmeCellType cellType = EnigmeCellType.toType(getBehaviorMap().getRGB(getHeight()-1-y, x));
				EnigmeCell cell = new EnigmeCell(x, y, cellType);
				setCell(x, y, cell);
			}
		}
	}
	
	// the type that describes the nature of a cell
	public enum EnigmeCellType {
		NULL(0), 
		WALL(-16777216),	// RGB code of black
		DOOR(-65536),		// RGB code of red
		WATER(-16776961),	// RGB code of blue
		TELEPORTER(-256),	// RGB code of yellow
		INDOOR_WALKABLE(-1), 
		OUTDOOR_WALKABLE(-14112955);

		// the type of the EnigmeCellType
		final int type;

		/**
		 * Constructor of EnigmeCellType
		 * @param type (int): the type of the EnigmeCellType
		 */
		EnigmeCellType(int type) {
			this.type = type;
		}
		
		/**
		 * Give the value of the enumerated type corresponding to the integer type
		 * @param (int) type: the integer we want to get the corresponding value
		 * @return (EnigmeCellType): the value of the enumerated type corresponding to the integer type
		 */
		static EnigmeCellType toType(int type) {
			for (EnigmeCellType value : EnigmeCellType.values()) {
				if (type == value.type) {
					return value;
				}
			}
			System.out.println(type);
			return NULL;
		}
	}
	
	public class EnigmeCell extends Cell {
		private EnigmeCellType nature;
		
		/**
		 * Default EnigmeCell Constructor
		 * @param (int) x: the x coordinate of the cell
		 * @param (int) y: the y coordinate of the cell
		 * @param (EnigmeCellType) EnigmeCell: the nature of EnigmeCell
		 */
		private EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x,y);
			nature = type;
		}
		
		/**
		 * Getter of the nature of the EnigmeCell
		 * @return (EnigmeCellType): the nature of the EnigmeCell
		 */
		public EnigmeCellType getNature() {
			return nature;
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
		protected boolean canEnter(Interactable entity) {
			if (nature.equals(EnigmeCellType.NULL) || nature.equals(EnigmeCellType.WALL) || nature.equals(EnigmeCellType.WATER)) {
				return false;
			}
			for (Interactable interactable : getEntities()) {
				if (interactable.takeCellSpace()) {
					return false;
				}
			}
			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			((EnigmeInteractionVisitor)v).interactWith(this);
		}
	}
}