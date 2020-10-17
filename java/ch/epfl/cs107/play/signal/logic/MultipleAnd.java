package ch.epfl.cs107.play.signal.logic;

import java.util.List;

public class MultipleAnd extends LogicSignal {
	
	// the List of Logics associated to And
	private List<Logic> e;
	
	/**
	 * Default Constructor MultipleAnd
	 * @param e (List<Logic>): the List of Logics associated to And
	 */
	public MultipleAnd(List<Logic> e) {
		this.e = e;
	}
	
	@Override
	public boolean isOn() {
		for (Logic s : e) {
			if (s == null || !s.isOn()) {
				return false;
			}
		}
		return true;
	}
}