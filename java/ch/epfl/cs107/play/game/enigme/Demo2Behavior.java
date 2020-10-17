package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {
	
	/**
	 * Constructor of Demo2Behaviour
	 * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
	 */
	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(getHeight()-1-y, x));
				Demo2Cell cell = new Demo2Cell(x, y, cellType);
				setCell(x, y, cell);
			}
		}
	}
	
	// the type that describes the nature of a cell
	public enum Demo2CellType {
		NULL(0), 
		WALL(-16777216),	// RGB code of black
		DOOR(-65536),		// RGB code of red
		WATER(-16776961),	// RGB code of blue
		INDOOR_WALKABLE(-1), 
		OUTDOOR_WALKABLE(-14112955);

		// the type of the Demo2CellType
		final int type;

		/**
		 * Constructor of Demo2CellType
		 * @param type (int): the type of the Demo2CellType
		 */
		Demo2CellType(int type) {
			this.type = type;
		}
		
		/**
		 * Give the value of the enumerated type corresponding to the integer type
		 * @param (int) type: the integer we want to get the corresponding value
		 * @return (Demo2CellType): the value of the enumerated type corresponding to the integer type
		 */
		static Demo2CellType toType(int type) {
			for (Demo2CellType value : Demo2CellType.values()) {
				if (type == value.type) {
					return value;
				}
			}
			return NULL;
		}
	}
	
	public class Demo2Cell extends Cell {
		
		// Nature of the cell
		private Demo2CellType nature;
		
		/**
		 * Constructor of Cell
		 * @param x (int): the x coordinate of the Cell
		 * @param y (int): the y coordinate of the Cell
		 * @param type (Demo2CellType): the nature of the Cell
		 */
		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x,y);
			nature = type;
		}
		
		/**
		 * Getter of the nature of the Demo2Cell
		 * @return nature (Demo2CellType) 
		 */
		public Demo2CellType getNature() {
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
			if (nature.equals(Demo2CellType.NULL) || nature.equals(Demo2CellType.WALL)) {
				return false;
			}
			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// No utility but needed because of the implementation of Part 3			
		}
	}
}