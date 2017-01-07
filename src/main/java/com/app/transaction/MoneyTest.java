package com.app.transaction;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class MoneyTest {

	@Test
	public void testCorrect() {
	//	fail("Not yet implemented");
		Money money = new Money(100,"PLN");
		money.getCurrency();
		System.out.print(money.getCurrency());
	}
	
	@Test
	public void testFake() {
	//	fail("Not yet implemented");
		Money money = new Money(100,"HWD");
		money.getCurrency();
		System.out.print(money.getCurrency());
	}

}
