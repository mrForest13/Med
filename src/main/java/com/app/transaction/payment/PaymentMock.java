package com.app.transaction.payment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentMock {
	private static final Logger logger = LoggerFactory.getLogger(PaymentMock.class);
	
	
	
	private Money money = null;
	
	public PaymentMock(Money money) {
		this.money = money;
	}
	
	public boolean buildAndExecuteRequest(String pesel){
		
		RollbackCash.save(pesel, money.amount().floatValue());
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		money.isOK();
		
		return true;
	}

}
