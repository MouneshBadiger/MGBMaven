package com.mgb.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.mgb.daos.IUserDao;
import com.mgb.forms.User;

public class UserDaoImpl implements IUserDao {
	@Autowired
	private HibernateTemplate template1; 
	
	private static volatile UserDaoImpl dao=null;
	public static UserDaoImpl getInstance (){
		if(dao==null){
			dao=new UserDaoImpl();
		}
		return dao;
	}
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public HibernateTemplate getTemplate1() {
		return template1;
	}
	public void setTemplate1(HibernateTemplate template1) {
		this.template1 = template1;
	}
	public User getUser(String email, String password) {
		String var[]={"email","pass"};
		String values[]={email,password};
		List list= template1.findByNamedParam("from User bo where bo.isActive=1 and bo.email=:email and bo.userLogin.password=:pass", var, values);
		return null;
	}
	public List<User> getAllUserInfo() {
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) template1.find("from User bo where bo.isActive=1");
		return list;
	}
	
	

}
