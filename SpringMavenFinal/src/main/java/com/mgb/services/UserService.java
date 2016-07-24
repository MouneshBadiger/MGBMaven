package com.mgb.services;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.mgb.bo.UserLogin;
import com.mgb.daoImpl.UserDaoImpl;
import com.mgb.daos.IRegistrationDao;
import com.mgb.daos.IUserDao;
import com.mgb.forms.User;

public class UserService {
	UserDaoImpl userDaoImpl;
	private static volatile UserService service=null;
	public static UserService getInstance(){
		if(service==null){
			service=new UserService();
		}
		return service;
	}
	//IUserDao userDao=new UserDaoImpl(); 
	
	public User getUser(ApplicationContext appContext, UserLogin userLogin) {
		IUserDao userDao=(IUserDao) appContext.getBean("userDao");
		User user=userDao.getUser(userLogin.getUser().getEmail(),userLogin.getPassword());
		return user;
	}

	public List<User> getAllUserInfo() {
		IUserDao userDao=UserDaoImpl.getInstance();
		List<User> userToList=userDaoImpl.getAllUserInfo();
		return userToList;
	}

	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	
}
