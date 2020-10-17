package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {
	
	// Instance of Logic that is always activated
	Logic TRUE = new Logic() {
		@Override
		public boolean isOn() {
			return true;
		}
	};
	
	// Instance of Logic that is always desactivated
	Logic FALSE = new Logic() {
		@Override
		public boolean isOn() {
			return false;
		}
	};
	
	/**
	 * Tell if the signal is activated or not
	 * @return (boolean): true if the signal is activated 
	 */
	boolean isOn();
	
	/**
	 * Getter for intensity
	 * @return (float): signal intensity, usually between 0.0 and 1.0
	 */
	default float getIntensity() {
		return isOn() ? 1.0f : 0.0f;
	}
	
	@Override
	default float getIntensity(float t) {
		return getIntensity();
	}
}