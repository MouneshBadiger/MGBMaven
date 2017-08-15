package com.mgb.services;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mgb.daos.IRegistrationDao;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;
@Component
public class RegisterService {
	private static volatile RegisterService service=null;
	public static RegisterService getInstance(){
		if(service==null){
			service=new RegisterService();
		}
		return service;
	}
	
	public String rigisterUser(ApplicationContext appContext, User register) throws Exception {
		IRegistrationDao dao=(IRegistrationDao) appContext.getBean("registrationDao");
		boolean checkDuplicate=dao.checkDuplicate(register.getMobileNo(),register.getId());
		if(checkDuplicate==false){
			boolean isAdded=dao.registerUser(register);
			if(isAdded)
				return "success";
			else
				return "error";
		}else{
			return "duplicate";
		}
		
	}

	public String saveSubscriber(ApplicationContext appContext, Subscriber subscriber) throws Exception {
		IRegistrationDao dao=(IRegistrationDao) appContext.getBean("registrationDao");
		boolean checkDuplicate=dao.checkDuplicate(subscriber.getMobileNo(),subscriber.getId());
		if(checkDuplicate==false){
			boolean isAdded=dao.saveSubscriber(subscriber);
			if(isAdded)
				return "success";
			else
				return "error";
		}else{
			return "duplicate";
		}
	}

	public Subscriber getSubscriberForEdit(ApplicationContext appContext, String userId) throws Exception {
		IRegistrationDao dao=(IRegistrationDao) appContext.getBean("registrationDao");
		Subscriber subscriber=dao.loadSubscriber(userId);
		return subscriber;
	}

	public boolean deleteSubscriber(ApplicationContext appContext, String userId) throws Exception {
		IRegistrationDao dao=(IRegistrationDao) appContext.getBean("registrationDao");
		return dao.deleteSubscriber(userId);
	}

	public User loadUser(ApplicationContext appContext, String userId) throws Exception {
		IRegistrationDao dao=(IRegistrationDao) appContext.getBean("registrationDao");
		User user=dao.loadUser(userId);
		return user;
	}
	

}
