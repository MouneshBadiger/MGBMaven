package com.mgb.daos;

import com.mgb.forms.Subscriber;
import com.mgb.forms.User;

public interface IRegistrationDao {

	boolean registerUser(User register);

	boolean checkDuplicate(String MobileNo,int userId) throws Exception;

	boolean saveSubscriber(Subscriber subscriber);

	Subscriber loadSubscriber(String userId)throws Exception;

	boolean deleteSubscriber(String userId) throws Exception;

	User loadUser(String userId)throws Exception;

	User loadUserByUsername(String userName);

}
