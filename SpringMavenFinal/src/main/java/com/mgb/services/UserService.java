package com.mgb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mgb.bo.AreaMaster;
import com.mgb.bo.UserLogin;
import com.mgb.daoImpl.AreaMasterDaoImpl;
import com.mgb.daoImpl.UserDaoImpl;
import com.mgb.daos.IAreaMasterDao;
import com.mgb.daos.IRegistrationDao;
import com.mgb.daos.IUserDao;
import com.mgb.forms.AreaMasterDto;
import com.mgb.forms.PaymentDetailsDTO;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;
@Component
public class UserService {
	@Autowired
	private PaymentService paymentService;
	UserDaoImpl userDaoImpl;
	@Autowired
	private AreaMasterDaoImpl areaDaoImpl;
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


	


	public List<AreaMasterDto> listAllAreas() throws Exception {
		IAreaMasterDao dao=areaDaoImpl;
		List<AreaMaster> boList=dao.listAllAreas();
		List<AreaMasterDto> dtoList=new ArrayList<AreaMasterDto>();
		if(boList!=null && !boList.isEmpty() ){
			for (AreaMaster bo : boList) {
				AreaMasterDto dto=new AreaMasterDto();
				dto.setId(bo.getId());
				dto.setName(bo.getName());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}


	public boolean checkForAreaDuplicate(AreaMasterDto areaMasterDto) throws Exception {
		IAreaMasterDao dao=areaDaoImpl;
		return dao.checkForAreaDuplicate(areaMasterDto);
	}


	public boolean addOrUpdateArea(AreaMasterDto areaMasterDto) throws Exception {
		IAreaMasterDao dao=areaDaoImpl;
		AreaMaster bo=new AreaMaster();
		bo.setId(areaMasterDto.getId());
		bo.setName(areaMasterDto.getName());
		bo.setIsActive(true);
		return dao.addOrUpdateArea(bo);
	}


	public boolean deletePaymentDef(String boId) throws Exception {
		IAreaMasterDao dao=areaDaoImpl;
		return dao.deleteArea(boId);
	}


	public AreaMasterDto editArea(String boId) throws Exception {
		IAreaMasterDao dao=areaDaoImpl;
		AreaMaster bo=dao.getAreaBo(boId);
		AreaMasterDto dto=new AreaMasterDto();
		if(bo!=null){
			dto.setId(bo.getId());
			dto.setName(bo.getName());
		}
		return dto;
	}
	
}
