package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Potion extends Usable {
	
	// the sprite that graphically represents the Berry
	private Sprite potion;
			
   /**
    * Default Potion constructor
	* @param area (Area): Owner area. Not null
	* @param orientation (Orientation): Initial orientation of the Potion in the Area. Not null
	* @param position (DiscreteCoordinate): Initial position of the Potion in the Area. Not null
	*/
	public Potion(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		potion = new Sprite("potion.1", 1, 1.f, this);
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
		potion.draw(canvas);		
	}

	@Override
	String getSpriteName() {
		return potion.getName();
	}
	
	/**
	 * It makes a player drink the potion. The effect of the potion is that the EnigmePlayer walks faster during a period of time. 
	 * The animation of the Sprite is adapted to the situation. 
	 * @param player (EnigmePlayer): the EnigmePlayer that drinks the potion
	 */
	public void isDrunk(EnigmePlayer player) {
		use();
		player.setIsStar(true);
		player.setSpeed();
	}
	
}