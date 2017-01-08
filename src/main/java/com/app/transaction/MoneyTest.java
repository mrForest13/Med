package com.app.transaction;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoneyTest {
	private static final Logger logger = LoggerFactory.getLogger(MoneyTest.class);
	
	public int externalPayment(Money money, String pesel){
		int random = 0 + (int)(Math.random() * 2);
		//0 success, 1 failure
		logger.info("External payment returns: "+ random);
		return random;  
	}
	 
	@Test
	public void testCorrect() {
		Money money = new Money(100,"PLN");
		money.getCurrency();
		//System.out.println(money.getCurrency());
	}
	
	@Test
	public void testFake() {
		Money money = new Money(100,"PLC");
		money.getCurrency();
		//System.out.print(money.getCurrency());
	}
	
	@Test
	public void testExternalPayment()
	{
		MoneyTest moneyTest = new MoneyTest();
		Money moneyExternalPayment = new Money(100,"PLN");
		System.out.println(moneyTest.externalPayment(moneyExternalPayment,"92120309742"));
		
	}

}
