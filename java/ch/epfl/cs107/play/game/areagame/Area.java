package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {
	// Context objects
	private Window window;
	private FileSystem fileSystem;
	
	// List of Actors inside the area
	private List<Actor> actors;
	
	// List of Actors waiting to be added (or removed) to (from) actors
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	
	// List of actors of type Interactor
	private List<Interactor> interactors;
	
	// Mapping of Interactable that will enter/leave the area
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;

	// Camera Parameter : actor on which the view is centered
	private Actor viewCandidate;
	// Effective center of the view
	private Vector viewCenter;
	
	// The behavior Map
	private AreaBehavior areaBehavior;
	
	// The Area state
	private boolean started;
	
	// The paused gaem state
	private boolean paused;

	/** Getter for the cameraScaleFactor
	 * @return (float): camera scale factor, assume it is the same in x and y direction 
	 */
    public abstract float getCameraScaleFactor();
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
    	// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.add(a);

		if (a instanceof Interactable) {
			errorOccured = errorOccured || !enterAreaCells((Interactable) a, ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.add((Interactor) a);
		}
		
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
			removeActor(a, true);
		}
    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
    	// Here decisions at the area level to decide if an actor
		// must be removed or not
		boolean errorOccured = !actors.remove(a);

		if (a instanceof Interactable) {
			errorOccured = errorOccured || !leaveAreaCells((Interactable) a, ((Interactable) a).getCurrentCells());
		}
		
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.remove((Interactor) a);
		}
		
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely removed, so add it to where it was");
			addActor(a, true);
		}
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
		return registeredActors.add(a);
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
    	return unregisteredActors.add(a);
    }
    
	/**
	 * Decides if entity can be removed from cells of the Area
	 * @param entity (Interactable): the entity that is removed from cells of the Area
	 * @param coordinates (List<DiscreteCoordinates>): the cells of the Area
	 * @return (boolean): true if the entity can be removed from cells of the Area
	 */
	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity, coordinates);
			return true;
		}
		return false;
	}
	
	/**
	 * Decides if entity can be added to cells of the Area
	 * @param entity (Interactable): the entity that is added to cells of the Area
	 * @param coordinates (List<DiscreteCoordinates>): the cells of the Area
	 * @return (boolean): true if the entity can be added to cells of the Area
	 */
	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity, coordinates);
			return true;
		}
		return false;
	}


    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
		return areaBehavior.getWidth();
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
		return areaBehavior.getHeight();
    }

    /** Getter for the window's keyboard
     * @return (Keyboard): the window's keyboard for inputs 
     */
    public final Keyboard getKeyboard() {
        return window.getKeyboard();
    }
    
    /**
	 * Getter for the area started state
	 * @return (boolean): true if the area has already started
	 */
	protected boolean getStarted() {
		return started;
	}
	
	/**
	 * Getter for the areaBehavior
	 * @return (AreaBehavior): areaBehaviour 
	 */
	public AreaBehavior getAreaBehavior() {
		return areaBehavior;
	}
    
    /**
	 * Setter for the actor on which the camera is focused on
	 * @param (Actor): the actor on which the camera will be focused on
	 */
	public final void setViewCandidate(Actor a) {
		viewCandidate = a;
	}
	
	/**
	 * Setter for the areaBahavior
	 * @param (AreaBahavior): the AreaBehaviour that will be attached to the Area
	 */
	protected final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab;
	}
    
    /**
	 * Add all actors contained in registeredActors to the list actors. 
	 * Remove all actors contained in registeredActors from the list actors.
	 * These two last steps take into account the potential veto from the grid.
	 * Empty out the lists registeredActors and unregisteredActors.
	 */
	private final void purgeRegistration() {
		for (Actor actor : registeredActors) {
			addActor(actor, false);
		}
		registeredActors.clear();

		for (Actor actor : unregisteredActors) {
			removeActor(actor, false);
		}
		unregisteredActors.clear();

		for (Interactable entry : interactablesToEnter.keySet()) {
			areaBehavior.enter(entry, entry.getCurrentCells());
		}
		interactablesToEnter.clear();

		for (Interactable entry : interactablesToLeave.keySet()) {
			areaBehavior.leave(entry, entry.getCurrentCells());
		}
		interactablesToLeave.clear();
	}

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.fileSystem = fileSystem;
		started = true;
		
		actors = new LinkedList<>();
		registeredActors = new LinkedList<>();
		unregisteredActors = new LinkedList<>();
		interactablesToEnter = new HashMap<>();
		interactablesToLeave = new HashMap<>();
		interactors = new LinkedList<>();
		
		viewCenter = Vector.ZERO;
		viewCandidate = null;

        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }

    @Override
    public void update(float deltaTime) {
    	Button pButton = getKeyboard().get(Keyboard.P);
		if (pButton.isReleased()) {
			paused = !paused;
		}
    	
    	if (!paused) {
    		purgeRegistration();
	    	
	    	for (Actor actor : actors) {
				actor.update(deltaTime);
			}
	
	    	for (Interactor interactor : interactors) {
	    		if (interactor.wantsCellInteraction()) {
	    			areaBehavior.cellInteractionOf(interactor);
	    		}
	    		if (interactor.wantsViewInteraction()) {
	    			areaBehavior.viewInteractionOf(interactor);
	    		}
	    	}
    	}

    	updateCamera();
    	
		for (Actor actor : actors) {
			actor.draw(window);
		}
    }


    /**
	 * Update the camera focus
	 */
	private void updateCamera() {
		if (viewCandidate != null) {
			viewCenter = viewCandidate.getPosition();
		}
		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
		window.setRelativeTransform(viewTransform);
	}

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend() {
        purgeRegistration();
    }


    @Override
    public void end() {
        started = false;
    }

}