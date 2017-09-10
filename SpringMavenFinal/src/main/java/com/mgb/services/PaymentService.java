package com.mgb.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mgb.bo.PaymentDefinition;
import com.mgb.bo.PaymentDetails;
import com.mgb.daoImpl.PaymentDaoImp;
import com.mgb.daos.IPaymentDao;
import com.mgb.forms.PaymentDefDTO;
import com.mgb.forms.PaymentDetailsDTO;
import com.mgb.forms.PaymentMonthlyDetailsDto;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;
import com.mgb.helpers.PaymentDefinitionHelper;
import com.mgb.to.YearWisePaymentDto;
import com.mgb.utils.ApplicationUtils;
@Component
public class PaymentService {
	@Autowired
	private PaymentDaoImp daoImpl;
	@Autowired
	private PaymentDefinitionHelper defHelper;
	
	public PaymentDetailsDTO getPaymentDetailsDTO(String userId) throws Exception {
		IPaymentDao dao=daoImpl;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		User user=dao.loadUser(userId);
		PaymentDetailsDTO paymentDto=new PaymentDetailsDTO();
		paymentDto.setUserId(String.valueOf(user.getId()));
		paymentDto.setUserName(user.getName());
		
		int totalPending=0;
		Map<String,String> pendingTillMonthMap=new LinkedHashMap<String,String>();
		Map<Integer,PaymentDefinition> paymentDefMap=dao.getPaymentDefinitionMap();
		Map<Integer,PaymentDetails> paymentComplMap=dao.getUserPaymentCompletedMap(userId);
		for (Entry<Integer,PaymentDefinition> defEntry : paymentDefMap.entrySet()) {
			Date date= sdf.parse(defEntry.getValue().getYear()+"-"+(defEntry.getValue().getMonth()+1)+"-"+01);
			Date subDate=null;
			if(user.getSubscriberDetails()!=null && user.getSubscriberDetails().getSubscribedDate()!=null){
				subDate=user.getSubscriberDetails().getSubscribedDate();
				subDate.setDate(1);
			}
			if(subDate!=null && date.compareTo(subDate)>=0){
				if(!paymentComplMap.containsKey(defEntry.getKey())){
					pendingTillMonthMap.put(String.valueOf(defEntry.getValue().getId()), ApplicationUtils.monthsArr[defEntry.getValue().getMonth()]+"-"+defEntry.getValue().getYear());
					totalPending=totalPending+defEntry.getValue().getAmount();
				}
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
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		if(paymentDetailsBoList!=null && !paymentDetailsBoList.isEmpty()){
			for (PaymentDetails bo : paymentDetailsBoList) {
				PaymentMonthlyDetailsDto to=new PaymentMonthlyDetailsDto();
				to.setId(bo.getId());
				to.setPaymentMonth(ApplicationUtils.monthsArr[bo.getPaymentDefinition().getMonth()]+"-"+bo.getPaymentDefinition().getYear());
				to.setPaymentMadeDate(formatter.format(bo.getPaymentDate()));
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

	public boolean addOrUpdatePaymentDef(PaymentDefDTO paymentDefDTO)throws Exception {
		IPaymentDao dao=daoImpl;
		PaymentDefinition bo=defHelper.convertToBo(paymentDefDTO);
		return dao.addOrUpdatePaymentDef(bo);
	}

	public PaymentDefDTO editPaymentDef(String boId) throws Exception{
		IPaymentDao dao=daoImpl;
		PaymentDefinition bo=dao.getPaymentDefBo(boId);
		return defHelper.convertToDTO(bo);
	}

	public boolean deletePaymentDef(String boId) throws Exception{ 
		IPaymentDao dao=daoImpl;
		return dao.deletePaymentDef(boId);
	}

	public List<PaymentDefDTO> listAllPaymentDefinition() throws Exception {
		IPaymentDao dao=daoImpl;
		List<PaymentDefinition> boList=dao.listAllPaymentDefinition();
		return defHelper.convertToDTOList(boList);
	}

	public boolean checkForDuplicate(PaymentDefDTO paymentDefDTO) throws Exception{
		IPaymentDao dao=daoImpl;
		return dao.checkForDuplicate(paymentDefDTO.getDefMonth(),paymentDefDTO.getDefYear());
	}

	public Map<Integer, PaymentDefinition> getPaymentDefinitionMap() throws Exception {
		IPaymentDao dao=daoImpl;
		return dao.getPaymentDefinitionMap();
	}

	public List<YearWisePaymentDto> getYearwisePaymentDetails(int year, String areaId) throws Exception {
		IPaymentDao dao=daoImpl;
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		List<PaymentDefinition> boList=dao.listAllPaymentDefinitionForYear(year);
		List<Subscriber> subscriberList=dao.getAllSubscriberList(areaId);
		List<YearWisePaymentDto>  dtoList=new ArrayList<YearWisePaymentDto>();
		
		for(Subscriber bo:subscriberList){
			YearWisePaymentDto dto=new YearWisePaymentDto();
			dto.setSubscriberName(bo.getName());
			dto.setId(bo.getId());
			dto.setAddress(bo.getAddress());
			dto.setPhoneNo(String.valueOf(bo.getMobileNo()));
			List<PaymentMonthlyDetailsDto> monthList=new LinkedList<PaymentMonthlyDetailsDto>();
			if(bo.getSubscriberDetails()!=null){
				dto.setPreBalance(bo.getSubscriberDetails().getBalance());
			}
			PaymentDetailsDTO paymentDetailsDTO=this.getPaymentDetailsDTO(String.valueOf(bo.getId()));
			if(paymentDetailsDTO!=null){
				
				int totalBalance=Integer.parseInt(paymentDetailsDTO.getTotalAmountPending());
				if(bo.getSubscriberDetails()!=null){
					totalBalance=totalBalance+Integer.parseInt(bo.getSubscriberDetails().getBalance());
				}
				dto.setTotalBalance(String.valueOf(totalBalance));
			}
			Map<Integer,PaymentDetails> paymentComplMap=dao.getUserPaymentCompletedMap(String.valueOf(bo.getId()));
			for(PaymentDefinition def:boList){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date= sdf.parse(def.getYear()+"-"+(def.getMonth()+1)+"-"+01);
				
				Date subDate=null;
				if(bo.getSubscriberDetails()!=null && bo.getSubscriberDetails().getSubscribedDate()!=null){
					subDate=bo.getSubscriberDetails().getSubscribedDate();
					subDate.setDate(1);
				}
				
				if(subDate!=null && date.compareTo(subDate)>=0){
					PaymentMonthlyDetailsDto to=new PaymentMonthlyDetailsDto();
					if(paymentComplMap.containsKey(def.getId())){
						to.setId(bo.getId());
						to.setPaymentMonth(ApplicationUtils.monthsArr[def.getMonth()]+"-"+def.getYear());
						Date paymentMadeDate=paymentComplMap.get(def.getId()).getPaymentDate();
						to.setPaymentMadeDate(formatter.format(paymentMadeDate));
						to.setAmount(String.valueOf(def.getAmount()));
						monthList.add(to);
					}else{
						to.setId(bo.getId());
						to.setPaymentMonth(ApplicationUtils.monthsArr[def.getMonth()]+"-"+def.getYear());
						to.setPaymentMadeDate("");
						to.setAmount("");
						monthList.add(to);
					}
				}else{
					PaymentMonthlyDetailsDto to=new PaymentMonthlyDetailsDto();
					to.setId(bo.getId());
					to.setPaymentMonth(ApplicationUtils.monthsArr[def.getMonth()]+"-"+def.getYear());
					to.setPaymentMadeDate("");
					to.setAmount("--");
					monthList.add(to);
				}
			}
			dto.setMonthList(monthList);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	public Map<Integer, String> getAreaMap()throws Exception {
		IPaymentDao dao=daoImpl;
		return dao.getAreaMap();
	}
	
}
