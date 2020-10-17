package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Enigme0;
import ch.epfl.cs107.play.game.enigme.area.Enigme1;
import ch.epfl.cs107.play.game.enigme.area.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	// Areas of our game
	private Area levelSelector = new LevelSelector();
	private Area level1 = new Level1();
	private Area level2 = new Level2();
	private Area level3 = new Level3();
	private Area enigme0 = new Enigme0();
	private Area enigme1 = new Enigme1();
	private Area enigme2 = new Enigme2();
	
    // The player is a concept of RPG games
	private EnigmePlayer player;

    // Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		addArea(levelSelector);
		addArea(level1);
		addArea(level2);
		addArea(level3);
		addArea(enigme0);
		addArea(enigme1);
		addArea(enigme2);
		setCurrentArea(levelSelector.getTitle(), false);
		player = new EnigmePlayer(getCurrentArea(), Orientation.UP, new DiscreteCoordinates(1, 3));
		player.enterArea(getCurrentArea(), new DiscreteCoordinates(1, 3));
		getCurrentArea().setViewCandidate(player);
		return true;
	}
	
	@Override
	public void update(float deltaTime) {
		if (player.getIsPassingDoor()) {
			player.unsetPassingDoor();
			player.leaveArea();
			setCurrentArea(player.getPassedDoor().getNameAreaDestination(), false);
			getCurrentArea().setViewCandidate(player);
			player.enterArea(getCurrentArea(), player.getPassedDoor().getArrivalCoordinates());
		}
		super.update(deltaTime);
	}


    @Override
    public int getFrameRate() {
        return 24;
    }
}