package riking;

import riking.stratgame.enums.EAtkType;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EAtkType a = EAtkType.Argn;
		System.out.println(a.toString());
		EAtkType b = EAtkType.get(5);
		System.out.println(b.toString());
		EAtkType c = EAtkType.get(-1);
		System.out.println(c.toString());
		
	}

}
