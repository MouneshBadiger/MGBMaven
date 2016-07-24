package com.mgb.daos;

import com.mgb.forms.User;

public interface IRegistrationDao {

	boolean registerUser(User register);

	boolean checkDuplicate(User register) throws Exception;

}
