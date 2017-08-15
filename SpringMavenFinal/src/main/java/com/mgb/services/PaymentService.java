package com.mgb.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mgb.bo.PaymentDefinition;
import com.mgb.bo.PaymentDetails;
import com.mgb.daoImpl.PaymentDaoImp;
import com.mgb.daos.IPaymentDao;
import com.mgb.forms.PaymentDetailsDTO;
import com.mgb.forms.PaymentMonthlyDetailsDto;
import com.mgb.forms.User;
import com.mgb.utils.ApplicationUtils;
@Component
public class PaymentService {
	@Autowired
	private PaymentDaoImp daoImpl;
	
	public PaymentDetailsDTO getPaymentDetailsDTO(String userId) throws Exception {
		IPaymentDao dao=daoImpl;
		User user=dao.loadUser(userId);
		PaymentDetailsDTO paymentDto=new PaymentDetailsDTO();
		paymentDto.setUserId(String.valueOf(user.getId()));
		paymentDto.setUserName(user.getName());
		
		int totalPending=0;
		Map<String,String> pendingTillMonthMap=new LinkedHashMap<String,String>();
		Map<Integer,PaymentDefinition> paymentDefMap=dao.getPaymentDefinitionMap(userId);
		Map<Integer,Integer> paymentComplMap=dao.getUserPaymentCompletedMap(userId);
		for (Entry<Integer,PaymentDefinition> defEntry : paymentDefMap.entrySet()) {
			if(!paymentComplMap.containsKey(defEntry.getKey())){
				pendingTillMonthMap.put(String.valueOf(defEntry.getValue().getId()), ApplicationUtils.monthsArr[defEntry.getValue().getMonth()]+"-"+defEntry.getValue().getYear());
				totalPending=totalPending+defEntry.getValue().getAmount();
			}
			
		}
		paymentDto.setTotalAmountPending(String.valueOf(totalPending));
		if(pendingTillMonthMap!=null && !pendingTillMonthMap.isEmpty()){
			paymentDto.setPendingTillMonthMap(pendingTillMonthMap);
			//for setting the first payment to be paid
			List<Entry<String,String>> entryList =new ArrayList<Map.Entry<String, String>>(pendingTillMonthMap.entrySet());
			Entry<String, String> lastEntry = entryList.get(entryList.size()-1);
			Map<String,String> pendingFromMonthMap=new LinkedHashMap<String,String>();
			pendingFromMonthMap.put(lastEntry.getKey(), lastEntry.getValue());
			paymentDto.setPendingFromMonthMap(pendingFromMonthMap);
		}
		
		/*pendingFromMonthMap.put(arg0, arg1)*/
		
		return paymentDto;
	}

	private List<PaymentMonthlyDetailsDto> convertBoListToMonthwise(List<PaymentDetails> paymentDetailsBoList) {
		List<PaymentMonthlyDetailsDto> dtoList=new ArrayList<PaymentMonthlyDetailsDto>();
		if(paymentDetailsBoList!=null && !paymentDetailsBoList.isEmpty()){
			for (PaymentDetails bo : paymentDetailsBoList) {
				PaymentMonthlyDetailsDto to=new PaymentMonthlyDetailsDto();
				to.setId(bo.getId());
				to.setPaymentMonth(ApplicationUtils.monthsArr[bo.getPaymentDefinition().getMonth()]+"-"+bo.getPaymentDefinition().getYear());
				to.setPaymentMadeDate(bo.getPaymentDate().getDate()+"-"+(bo.getPaymentDate().getMonth()+1)+"-"+bo.getPaymentDate().getYear());
				to.setAmount(String.valueOf(bo.getPaymentDefinition().getAmount()));
				dtoList.add(to);
			}
		}
		return dtoList;
	}

	public boolean addMonthlyPayment(PaymentDetailsDTO paymentDto) throws Exception {
		IPaymentDao dao=daoImpl;
		List<Entry<String,String>> boList=new ArrayList<Entry<String,String>>(paymentDto.getPendingTillMonthMap().entrySet());
		List<PaymentDetails> boSaveList=new ArrayList<PaymentDetails>();
		
		for (int i=boList.size()-1;i>=0;i--) {
			Entry<String,String> entry=boList.get(i);
			PaymentDetails bo=new PaymentDetails();
			User user=new User();
			user.setId(Integer.parseInt(paymentDto.getUserId()));
			bo.setUserId(user);
			bo.setPaymentDate(new Date());
			PaymentDefinition def=new PaymentDefinition();
			def.setId(Integer.parseInt(entry.getKey()));
			bo.setPaymentDefinition(def);
			bo.setIsActive(true);
			
			boSaveList.add(bo);
			if(paymentDto.getPendingTillMonth().equals(entry.getKey())){
				break;
			}
		}
		return dao.savePaymentDetailsList(boSaveList,paymentDto.getUserId());
		
	}

	public List<PaymentMonthlyDetailsDto> getMonthlyDetailsList(String userId) throws Exception {
		IPaymentDao dao=daoImpl;
		List<PaymentDetails> paymentDetailsBoList=dao.getaPaymentDetailsBoList(userId);
		List<PaymentMonthlyDetailsDto> monthlyDetailsList=this.convertBoListToMonthwise(paymentDetailsBoList);
		return monthlyDetailsList;
	}

	public boolean cancelMonthlyPayment(String boId) throws Exception {
		IPaymentDao dao=daoImpl;
		return dao.cancelMonthlyPaymennt(boId);
	}
	
}
