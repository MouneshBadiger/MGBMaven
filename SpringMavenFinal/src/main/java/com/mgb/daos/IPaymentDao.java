package com.mgb.daos;

import java.util.List;
import java.util.Map;

import com.mgb.bo.PaymentDefinition;
import com.mgb.bo.PaymentDetails;
import com.mgb.forms.User;

public interface IPaymentDao {

	Map<Integer, PaymentDefinition> getPaymentDefinitionMap(String userId) throws Exception;

	Map<Integer, Integer> getUserPaymentCompletedMap(String userId) throws Exception;

	User loadUser(String userId) throws Exception;

	boolean savePaymentDetailsList(List<PaymentDetails> boSaveList, String userId)throws Exception;

	List<PaymentDetails> getaPaymentDetailsBoList(String userId)throws Exception;

	boolean cancelMonthlyPaymennt(String boId)throws Exception;

}
