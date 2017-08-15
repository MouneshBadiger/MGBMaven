package com.mgb.daos;

import java.util.List;

import com.mgb.forms.User;

public interface IUserDao {

	User getUser(String email, String password);

	List<User> getAllUserInfo() throws Exception;

}
