package ch.epfl.cs107.play.signal.logic;

public class And extends LogicSignal {
	
	// the Logics associated to And
	private Logic s1;
	private Logic s2;
	
	/**
	 * Default Constructor And
	 * @param s1 (Logic): a Logic associated to And
	 * @param s2 (Logic): a Logic associated to And
	 */
	public And(Logic s1, Logic s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	@Override
	public boolean isOn() {
		if (s1 != null && s2 != null && s1.isOn() && s2.isOn()) {
			return true;
		}
		return false;
	}
}