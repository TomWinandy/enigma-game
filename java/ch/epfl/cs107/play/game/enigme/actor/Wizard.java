package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class Wizard extends ObjectGiver {

	private Sprite[] wizard;

	/**
	 * Wizard constructor, gives Potions of speed
	 * @param area
	 * @param orientation
	 * @param position
	 */
	public Wizard(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, new Potion(area, orientation, position));
		wizard = new Sprite[4];
		for (int i = 0; i < wizard.length; i++) {
			wizard[i] = new Sprite("old.man.3", 0.5f * 2, 0.65625f * 2, this, new RegionOfInterest(i * 16, 0, 16, 21));
		}
		((Usable)(getCollectable())).use(); // Makes it possible for the wizard to give the potion the first time
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
		wizard[getOrientationToInt()].draw(canvas);
	}
	
	@Override
	protected void setOrientation(Orientation orientation) {
		super.setOrientation(orientation.opposite());
	}
	
	/**
	 * Checks if the wizard agrees to give the potion. It will give it only if the player has used the last potion he gave him
	 * @return (boolean)
	 */
	public boolean agreeToGive() {
		if (((Usable)(getCollectable())).getUsed()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
}
