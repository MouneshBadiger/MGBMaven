package com.mgb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mgb.bo.UserLogin;
import com.mgb.daoImpl.UserDaoImpl;
import com.mgb.daos.IRegistrationDao;
import com.mgb.daos.IUserDao;
import com.mgb.forms.PaymentDetailsDTO;
import com.mgb.forms.User;
@Component
public class UserService {
	@Autowired
	private PaymentService paymentService;
	UserDaoImpl userDaoImpl;
	private static volatile UserService service=null;
	public static UserService getInstance(){
		if(service==null){
			service=new UserService();
		}
		return service;
	}
	//IUserDao userDao=new UserDaoImpl(); 
	

	public List<User> getAllUserInfo() throws Exception {
		IUserDao userDao=UserDaoImpl.getInstance();
		List<User> userToList=userDaoImpl.getAllUserInfo();
		//code added for setting pending amount for all users permamance may be effected
		for (User user : userToList) {
			PaymentDetailsDTO dto=paymentService.getPaymentDetailsDTO(String.valueOf(user.getId()));
			user.setPendingAmount(dto.getTotalAmountPending());
		}
		//pending amount ends
		
		return userToList;
	}

	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	
}
