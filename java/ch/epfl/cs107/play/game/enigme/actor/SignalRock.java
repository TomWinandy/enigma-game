package ch.epfl.cs107.play.game.enigme.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalRock extends AreaEntity {
	
	// the signal that determines if the SignalRock appears or not 
	private Logic logic;
	
	// the sprite that graphically represents the SignalRock
	private Sprite rock;
	
	// the coordinates of the cell the SignalRock  is occupying 
	private List<DiscreteCoordinates> currentCells;
	
	/**
	 * SignalRock constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the SignalRock in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the SignalRock in the Area. Not null
	 * @param logic (Logic): the signal that determines if the SignalRock appears or not 
	 * @param size (float): size of the sprite of the SignalRock
	 */
	public SignalRock(Area area, Orientation orientation, DiscreteCoordinates position, Logic logic, float size) {
		super(area, orientation, position);
		this.logic = logic;
		currentCells = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				currentCells.add(position.jump(new Vector(i, j)));
			}
		}
		rock = new Sprite("rock.3", size, size, this);
	}
	
	/**
	 * Default SignalRock constructor
	 * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the SignalRock in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the SignalRock in the Area. Not null
	 * @param logic (Logic): the signal that determines if the SignalRock appears or not 
	 */
	public SignalRock(Area area, Orientation orientation, DiscreteCoordinates position, Logic logic) {
		this(area, orientation, position, logic, 1);
	}


	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return currentCells;
	}

	@Override
	public boolean takeCellSpace() {
		return !logic.isOn();
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (!logic.isOn()) {
			rock.draw(canvas);
		}
	}
}