package ch.epfl.cs107.play.game.demo1;


import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {
	
	// First actor of Demo1
	private Actor actor1;
	// Second actor of Demo1
	private Actor rock;
	// Text associated with rock 
	private final TextGraphics boum = new TextGraphics("BOUM !!!", 0.12f, Color.RED); 
	// The content to be displayed for the Demo1
	private Window window;
	// The file system associated with Demo1
	private FileSystem fileSystem;
	
	@Override
	public String getTitle() {
		return "Demo1";
	}
	
	@Override 
	public int getFrameRate() {
		return 24;
	}
	
	@Override 
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.fileSystem = fileSystem;
		
		Transform viewTransform = Transform.I.scaled(1.1f).translated(new Vector(-0.0f, 0.0f));
		window.setRelativeTransform(viewTransform);
		
		float radius = 0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));
		rock = new MovingRock(new Vector(0.2f, 0.2f), "Pierre de feu !");
		return true;
	}
	
	@Override 
	public void update(float deltaTime) {
		Keyboard keyboard = window.getKeyboard();
		Button downArrown = keyboard.get(Keyboard.DOWN);
			
		if (downArrown.isDown()) {
			rock.update(deltaTime);
		}
		
		double distance = distance(actor1, rock);
		
		if (distance <= ((Circle)(((ShapeGraphics)(((GraphicsEntity)actor1).getGraphics())).getShape())).getRadius()){
			boum.draw(window);
		}
		
		actor1.draw(window);
		rock.draw(window);
	}
	
	/**
	 * Measure the distance between two actors
	 * @param a (Actor): the first actor, not null
	 * @param b (Actor): the second actor, not null
	 * @return (double): the distance between a and b
	 */
	private double distance(Actor a, Actor b) {
		return a.getPosition().sub(b.getPosition()).getLength();
	}
	
	@Override
	public void end() {

	}
}