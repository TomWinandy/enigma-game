package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal {
	
	// the Logic of which Not is the negation
	private Logic s;
	
	/**
	 * Default Constructor Not
	 * @param s (Logic): the Logic associated to Not
	 */
	public Not(Logic s) {
		this.s = s;
	}
	
	@Override
	public boolean isOn() {
		if (s != null) {
			return  !s.isOn();
		}
		return false;
	}
}