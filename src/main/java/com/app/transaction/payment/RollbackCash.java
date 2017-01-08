package com.app.transaction.payment;

import java.util.HashMap;
import java.util.Map;

public class RollbackCash {

	private static RollbackCash soleInstance = new RollbackCash();
	private static Map<String, Float> rollback = new HashMap<String, Float>();

	private RollbackCash() {
	}

	public static RollbackCash getInstance() {
		return soleInstance;
	}

	public static void save(String pesel, float cash) {
		rollback.put(pesel, cash);
	}

	public static float get(String pesel) {
		if (rollback.get(pesel)==null)
			return 0f;
		else {
			
			float value = rollback.get(pesel);;
			rollback.remove(pesel);
			
			return value;
		}
	}
}
