package com.app.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.transaction.TransactionScript;
import com.app.transaction.session.UpdateOldSessionState;

public class ScheduledSessionTask {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledSessionTask .class);

    public void updateSessionState() throws Exception {
    	
    	logger.info("Update old session state!");
    	
    	TransactionScript transactionScript = new UpdateOldSessionState();
		transactionScript.run();
    }
	
}
