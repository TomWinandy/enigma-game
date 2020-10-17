package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Activable;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.ObjectGiver;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Switchable;
import ch.epfl.cs107.play.game.enigme.actor.Wizard;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	
	/**
	 * Simulate an interaction between an enigme Interactor and a Collectable
	 * @param collectable (Collectable), not null
	 */
	default void interactWith(Collectable collectable) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and a Switchable
	 * @param switchable (Switchable), not null
	 */
	default void interactWith(Switchable switchable) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an Activable
	 * @param activable (Activable), not null
	 */
	default void interactWith(Activable activable) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and a Door
	 * @param door (Door), not null
	 */
	default void interactWith(Door door) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Player
	 * @param door (Door), not null
	 */
	default void interactWith(EnigmePlayer player) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an enigme Cell
	 * @param cell (EnigmeCell), not null
	 */
	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and a PressureSwitch
	 * @param pressureSwitch (PressureSwitch), not null
	 */
	default void interactWith(PressureSwitch pressureSwitch) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and an ObjectGiver
	 * @param objectGiver (ObjectGiver), not null
	 */
	default void interactWith(ObjectGiver objectGiver) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate an interaction between an enigme Interactor and a Wizard
	 * @param wizard (Wizard), not null
	 */
	default void interactWith(Wizard wizard) {
		// by default the interaction is empty
	}
}