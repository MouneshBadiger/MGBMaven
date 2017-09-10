package com.mgb.daos;

import java.util.List;
import java.util.Map;

import com.mgb.bo.PaymentDefinition;
import com.mgb.bo.PaymentDetails;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;

public interface IPaymentDao {

	Map<Integer, PaymentDefinition> getPaymentDefinitionMap() throws Exception;

	Map<Integer, PaymentDetails> getUserPaymentCompletedMap(String userId) throws Exception;

	User loadUser(String userId) throws Exception;

	boolean savePaymentDetailsList(List<PaymentDetails> boSaveList, String userId)throws Exception;

	List<PaymentDetails> getaPaymentDetailsBoList(String userId)throws Exception;

	boolean cancelMonthlyPaymennt(String boId)throws Exception;

	boolean addOrUpdatePaymentDef(PaymentDefinition bo) throws Exception;

	PaymentDefinition getPaymentDefBo(String boId) throws Exception;

	boolean deletePaymentDef(String boId)throws Exception;

	List<PaymentDefinition> listAllPaymentDefinition() throws Exception;

	boolean checkForDuplicate(String defMonth, String defYear)throws Exception;

	List<PaymentDefinition> listAllPaymentDefinitionForYear(int year);

	List<Subscriber> getAllSubscriberList(String areaId) throws Exception;

	Map<Integer, String> getAreaMap()throws Exception;

}
