package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
	
	// Camera zoom of the game
	public final static int CAMERA_SCALE_FACTOR = 22;

	// Areas of our game
	private Area room0 = new Room0();
	private Area room1 = new Room1();
	
	// Position to land on different areas
	private DiscreteCoordinates coordinatesToLandOnRoom0 = new DiscreteCoordinates(5, 5);
	private DiscreteCoordinates coordinatesToLandOnRoom1 = new DiscreteCoordinates(5, 2);

	// The player of Demo2
	private Demo2Player player;

	@Override
	public int getFrameRate() {
		return 24;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		addArea(room0);
		addArea(room1);
		setCurrentArea(room0.getTitle(), false);
		player = new Demo2Player(getCurrentArea(), coordinatesToLandOnRoom0);
		getCurrentArea().setViewCandidate(player);
		return true;
	}
	
	@Override
	public void update(float deltaTime) {
		if (player.getCrossingADoor()) {
			Area roomToEnter;
			DiscreteCoordinates coordinatesToBeAt;
			if (getCurrentArea().equals(room0)) {
				roomToEnter = room1;
				coordinatesToBeAt = coordinatesToLandOnRoom1;
			} else {
				roomToEnter = room0;
				coordinatesToBeAt = coordinatesToLandOnRoom0;
			}
			player.leaveArea();
			setCurrentArea(roomToEnter.getTitle(), false);
			player.enterArea(getCurrentArea(), coordinatesToBeAt);
			getCurrentArea().setViewCandidate(player);
			player.setCrossingADoor(false);
		}
		super.update(deltaTime);
	}
}