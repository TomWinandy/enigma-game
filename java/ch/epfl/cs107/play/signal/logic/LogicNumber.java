package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class LogicNumber extends LogicSignal {
	
	// the number that characterize the LogicNumber
	private float nb;
	// the List of Logic that characterize the LogicNumber
	private List<Logic> e;
	
	/**
	 * Default Constructor LogicNumber
	 * @param nb (float): the number that characterize the LogicNumber
	 * @param e (List<Logic>): the List of Logic that characterize the LogicNumber
	 */
	public LogicNumber(float nb, List<Logic> e) {
		this.nb = nb;
		this.e = e;
	}
	
	@Override
	public boolean isOn() {
		if (e.size() > 12 || nb < 0.0f || nb > Math.pow(2, e.size())) {
			return false;
		}
		float nbSignal = 0;
		for (int i = 0; i < e.size(); ++i) {
			nbSignal += Math.pow(2, i) * e.get(i).getIntensity();
		}
		if (nbSignal == nb) {
			return true;
		}
		return false;
	}
}