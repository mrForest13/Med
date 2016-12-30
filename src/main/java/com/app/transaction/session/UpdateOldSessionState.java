package com.app.transaction.session;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.app.model.session.Session;
import com.app.registry.Registry;
import com.app.transaction.TransactionScript;

public class UpdateOldSessionState extends TransactionScript{

	@Override
	public void run() throws Exception {
		
		List<Session> list = Registry.sessionFinder().getAll();
		
		Timestamp startSession = new Timestamp(Calendar.getInstance().getTime().getTime());
		
		for(Session s : list) {
			if(startSession.getTime()-s.getSessionDate().getTime()>900000) {
				s.setSessionIsActive(false);
				s.update();
			}
		}
		
	}

}
