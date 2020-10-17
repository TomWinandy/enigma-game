package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
	// Animation duration in frame number
	private final static int ANIMATION_DURATION = 5;

	// the sprite that graphically represents the EnigmePlayer
	private Sprite[][] spritesNormal;
	private Sprite[][] spritesStar1;
	private Sprite[][] spritesStar2;
	// tell if the EnigmePlayer is passing a door or not
	private boolean isPassingDoor;
	// the last door that the EnigmePlayer passed
	private Door lastPassedDoor;

	// the handler of the EnigmePlayer
	private final EnigmePlayerHandler handler;

	// the backpack of the EnigmePlayer
	private Backpack backpack;

	// the player moving state
	private int movingState;

	// a simple counter used for the animation of the Sprite
	private int compteur;

	// tell if the player is in fast mode or not
	private boolean isStar;

	// the time a potion stays active after it has been used
	private float defaultActivationTimePotion = 1f;

	// the time since isStar has been activated
	private float activeSince;
	
	// the current animation duration for the player's move
	private int currentAnimationDuration;

	/**
	 * Default EnigmePlayer constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param coordinates (Coordinate): Initial position of the entity. Not null
	 * @param orientation (Orientation): Initial orientation of the entity. Not null
	 */
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		movingState = 0;
		spritesNormal = new Sprite[4][4];
		spritesStar1 = new Sprite[4][4];
		spritesStar2 = new Sprite[4][4];
		for (int i = 0; i <= 3; ++i) {
			for (int j = 0; j <= 3; j++) {
				spritesNormal[i][j] = new Sprite("max.new.1", 0.5f * 2, 0.65625f * 2, this,
						new RegionOfInterest(j * 16, i * 21, 16, 21), Vector.ZERO);
				spritesStar1[i][j] = new Sprite("max.ghost", 0.5f * 2, 0.65625f * 2, this,
						new RegionOfInterest(j * 16, i * 21, 16, 21), Vector.ZERO);
				spritesStar2[i][j] = new Sprite("max.invert", 0.5f * 2, 0.65625f * 2, this,
						new RegionOfInterest(j * 16, i * 21, 16, 21), Vector.ZERO);
			}
		}
		isPassingDoor = false;
		isStar = false;
		compteur = 0;
		activeSince = defaultActivationTimePotion;
		handler = new EnigmePlayerHandler();
		setSpeed();
		backpack = new Backpack(10);
		currentAnimationDuration = ANIMATION_DURATION;
	}

	/**
	 * Another EnigmePlayer constructor
	 * 
	 * @param area
	 * @param coordinates
	 */
	public EnigmePlayer(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
	}

	public EnigmePlayer getPlayer() {
		return this;
	}

	public void setIsStar(boolean is) {
		isStar = is;
	}

	public void setSpeed() {
		if (isStar) {
			currentAnimationDuration = 2;
		} else {
			currentAnimationDuration = ANIMATION_DURATION;
		}
	}

	/**
	 * Register current entity in a new Area and set its position as given
	 * 
	 * @param area
	 * @param position
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		getOwnerArea().registerActor(this);
		setCurrentPosition(position.toVector());
		backpack.setRelativePosition();
		resetMotion();
		if (lastPassedDoor != null) {
			setOrientation(lastPassedDoor.getExitingOrientation());
		}
	}

	/**
	 * Unregister current entity form its Area
	 */
	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
		setOwnerArea(null);
	}

	/**
	 * Set true for isPassingDoor
	 * 
	 * @param door (Door): the door the EnigmePlayer is passing
	 */
	public void setIsPassingDoor(Door door) {
		isPassingDoor = true;
		lastPassedDoor = door;
	}

	/**
	 * Set false for isPassingDoor
	 * 
	 * @param door (Door): the door the EnigmePlayer is passing
	 */
	public void unsetPassingDoor() {
		isPassingDoor = false;
	}

	/**
	 * Getter for lastPassedDoor
	 * 
	 * @return (Door): the last door the EnigmePlayer has passed
	 */
	public Door getPassedDoor() {
		return lastPassedDoor;
	}

	/**
	 * Getter for isPassingDoor
	 * 
	 * @return (boolean): true if the EnigmePlayer is passing a Door
	 */
	public boolean getIsPassingDoor() {
		return isPassingDoor;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	@Override
	public void update(float deltaTime) {		
		Keyboard keyboard = getOwnerArea().getKeyboard();

		Button leftArrown = keyboard.get(Keyboard.LEFT);
		if (leftArrown.isDown()) {
			if (getOrientation().equals(Orientation.LEFT)) {
				move(currentAnimationDuration);
			} else {
				setOrientation(Orientation.LEFT);
			}
		}

		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		if (rightArrow.isDown()) {
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(currentAnimationDuration);
			} else {
				setOrientation(Orientation.RIGHT);
			}
		}

		Button downArrow = keyboard.get(Keyboard.DOWN);
		if (downArrow.isDown()) {
			if (getOrientation().equals(Orientation.DOWN)) {
				move(currentAnimationDuration);
			} else {
				setOrientation(Orientation.DOWN);
			}
		}

		Button upArrow = keyboard.get(Keyboard.UP);
		if (upArrow.isDown()) {
			if (getOrientation().equals(Orientation.UP)) {
				move(currentAnimationDuration);
			} else {
				setOrientation(Orientation.UP);
			}
		}
		
		Button spaceButton = keyboard.get(Keyboard.SPACE);
		if (spaceButton.isPressed()) {
			if (!backpack.isFull()) {
				backpack.addCollectable(new Potion(getOwnerArea(), Orientation.DOWN, new DiscreteCoordinates(0, 0)));
			}
		}

		Button lButton = keyboard.get(Keyboard.L);
		if (lButton.isPressed()) {
			backpack.placeObject();
		}

		Button tab = keyboard.get(Keyboard.TAB);
		if (tab.isPressed()) {
			backpack.increaseCursor();
		}

		Button dButton = keyboard.get(Keyboard.D);
		if (dButton.isPressed()) {
			backpack.drinkObject();
		}

		if (isStar && (activeSince > defaultActivationTimePotion)) {
			isStar = false;
			setSpeed();
		}
		
		activeSince += deltaTime;

		if (getIsMoving()) {
			++movingState;
		} else {
			movingState = 0;
		}
		
		if (isStar) {
			compteur++;
		}
		
		super.update(deltaTime);

	}

	@Override
	public void draw(Canvas canvas) {
		movingState = movingState % 4;

		if (isStar) {
			if (compteur % 4 == 0 || compteur % 4 == 1) {
				spritesStar1[movingState][getOrientationToInt()].draw(canvas);
			} else if (compteur % 4 == 2 || compteur % 4 == 3) {
				spritesStar2[movingState][getOrientationToInt()].draw(canvas);
			}
		} else {
			spritesNormal[movingState][getOrientationToInt()].draw(canvas);
		}

		backpack.draw(canvas);
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
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		if (!backpack.isEmpty() && backpack.getItemPointed() instanceof Stick) {
			return Arrays.asList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()), getCurrentMainCellCoordinates().jump(getOrientation().toVector().mul(2f)));
		}
		return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		if (getOwnerArea().getKeyboard().get(Keyboard.L).isPressed()) {
			return true;
		}
		return false;
	}

	/**
	 * Specific interaction handler for an EnigmePlayer
	 */
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		@Override
		public void interactWith(Door door) {
			setIsPassingDoor(door);
		}

		@Override
		public void interactWith(Collectable collectable) {
			if (backpack.addCollectable(collectable)) {
				collectable.collect();
			}
		}

		@Override
		public void interactWith(Switchable switchable) {
			switchable.swap();
		}

		@Override
		public void interactWith(Activable activable) {
			activable.active();
		}

		@Override
		public void interactWith(PressureSwitch pressureSwitch) {
			if (getIsMoving() && getCurrentMainCellCoordinates().equals(getTargetMainCellCoordinates())) {
				pressureSwitch.swap();
			}
		}
		
		@Override
		public void interactWith(Wizard wizard) {
			wizard.setOrientation(getOrientation());
			if (!backpack.isFull() && wizard.agreeToGive()) {
				backpack.addCollectable(wizard.getCollectable());
				((Potion)(wizard.getCollectable())).resetUsed();
			}
		}
	}

	private class Backpack implements Graphics {

		private final static int MAX_BACKPACK_SIZE = 10;

		private List<Collectable> inventory;
		private List<ImageGraphics> images;

		private float DX;
		private float DY;

		private int cursorId;
		private ImageGraphics cursor;

		public Backpack(int size) {
			if (size <= 0 || size > 20) {
				size = 10;
			}
			images = new LinkedList<>();
			inventory = new LinkedList<>();
			setRelativePosition();
			cursorId = 0;
			cursor = new ImageGraphics(ResourcePath.getSprite("shield.2"), 0.35f, 0.35f);
		}
		
		public Backpack() {
			this(10);
		}

		private void setRelativePosition() {
			DX = getOwnerArea().getCameraScaleFactor() / 2 - 1;
			DY = getOwnerArea().getCameraScaleFactor() / 2 - 1;
		}

		/**
		 * Places the first object collected and not yet replaced
		 * 
		 * @return boolean whether it has been possible to place the object or not
		 */
		private boolean placeObject() {
			if (!getIsMoving()) {
				if (!inventory.isEmpty()) {
					if (getOwnerArea().getAreaBehavior().canEnter(inventory.get(cursorId), getFieldOfViewCells())) {
						Collectable collectable = inventory.get(cursorId);
						ImageGraphics image = images.get(cursorId);
						collectable.pose(getOwnerArea(), getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
						inventory.remove(collectable);
						images.remove(image);
						if (cursorId != 0) {
							--cursorId;
						}
						return true;
					}
				}
			}
			return false;
		}

		/**
		 * Drink the potion selected by the cursor
		 * 
		 * @return boolean whether it has been possible to drink the potion or not
		 */
		private boolean drinkObject() {
			if (!inventory.isEmpty()) {
				if (inventory.get(cursorId) instanceof Potion) {
					Potion potion = (Potion) inventory.get(cursorId);
					ImageGraphics image = images.get(cursorId);
					potion.isDrunk(getPlayer());
					inventory.remove(potion);
					images.remove(image);
					activeSince = 0;
					if (cursorId != 0) {
						--cursorId;
					}
					return true;
				}
			}
			return false;
		}

		/**
		 * Adds the collectable to the lists
		 * 
		 * @param collectable
		 * @return (boolean) whether it has been possible to add the collectable or not
		 */
		private boolean addCollectable(Collectable collectable) {
			if (inventory.size() < MAX_BACKPACK_SIZE) {
				inventory.add(collectable);
				images.add(new ImageGraphics(collectable.getSpriteName(), 1f, 1f));
				return true;
			}
			return false;
		}

		@Override
		public void draw(Canvas canvas) {
			int i = 0;
			for (ImageGraphics image : images) {
				Transform transform = Transform.I.translated(canvas.getPosition().sub(DX - i, DY));
				image.setRelativeTransform(transform);
				image.draw(canvas);
				++i;
			}
			if (images.size() >= 2) {
				Transform cursorTransform = Transform.I
						.translated(canvas.getPosition().sub(DX - cursorId - 0.35f, DY - 1f));
				cursor.setRelativeTransform(cursorTransform);
				cursor.draw(canvas);
			}
		}

		/**
		 * Increases the cursor selecting the collectable to pose
		 */
		private void increaseCursor() {
			if (images.size() != 0) {
				++cursorId;
				cursorId %= images.size();
			}
		}
		
		/**
		 * Tells if the backpack is full or not
		 * @return (boolean)
		 */
		private boolean isFull() {
			if (inventory.size() == MAX_BACKPACK_SIZE) {
				return true;
			}
			return false;
		}
		
		/**
		 * Getter for the item currently selected by the cursor
		 * @return (Collectable)
		 */
		private Collectable getItemPointed() {
			return inventory.get(cursorId);
		}
		
		/**
		 * Indicates whether the backpack is empty or not
		 * @return (boolean)
		 */
		private boolean isEmpty() {
			return inventory.isEmpty();
		}
	}
}