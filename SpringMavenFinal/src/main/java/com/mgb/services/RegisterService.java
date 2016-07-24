package com.mgb.services;

import org.springframework.context.ApplicationContext;

import com.mgb.daoImpl.RegistrationDaoImpl;
import com.mgb.daos.IRegistrationDao;
import com.mgb.forms.User;

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
		boolean checkDuplicate=dao.checkDuplicate(register);
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
	

}
