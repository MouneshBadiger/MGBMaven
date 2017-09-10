package com.mgb.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgb.bo.AreaMaster;
import com.mgb.bo.PaymentDetails;
import com.mgb.forms.AreaMasterDto;
import com.mgb.forms.PaymentDefDTO;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;
import com.mgb.services.PaymentService;
import com.mgb.services.RegisterService;
import com.mgb.services.UserService;
import com.mgb.to.YearWisePaymentDto;

@Controller()
public class AdminController {
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	UserService userService;
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping("/admin/userInfo")
	public String getAllUsersInfo(Model model,@RequestParam(value = "generateReport",required = false) String generateReport){
		try {
			boolean isAdmin=false;
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 for (GrantedAuthority authority : auth.getAuthorities()) {
				 if(authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")){
					 isAdmin=true;
					 break;
				 }
			 }
			if( isAdmin){
				List<User> userList=userService.getAllUserInfo();
				model.addAttribute(userList);
				if(generateReport!=null && generateReport.equalsIgnoreCase("true")){
					return "generateReportUserInfo";
				}else{
					return "userInfo";
				}
				
			}else{
				model.addAttribute("error","Sorry, seems like you do not have access.");
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	@RequestMapping("/admin")
	public String welcomeAdmin(Model model){
		return "adminPage";
	}
	@RequestMapping("admin/addSubscriber")
	public String openRegisterPage(Model model){
		try {
			Subscriber subscriber=new Subscriber();
			model.addAttribute("subscriber", subscriber);
			Map<Integer, String> areaMap = paymentService.getAreaMap();
			model.addAttribute("areaMap",areaMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSubscriber";
	}
	@RequestMapping("admin/saveSubscriber")
	public String registration(@Valid @ModelAttribute("subscriber") Subscriber subscriber, BindingResult result,Model model) throws Exception{
		String regResult="";
		try{
			
			model.addAttribute("subscriber", subscriber);
			if(result.hasErrors()){
				Map<Integer, String> areaMap = paymentService.getAreaMap();
				model.addAttribute("areaMap",areaMap);
				return "addSubscriber";
			}else{
				//register as user
				subscriber.setRole("ROLE_USER");
				if(subscriber.getAreaStrId()!=null){
					AreaMaster areaMaster=new AreaMaster();
					areaMaster.setId(Integer.parseInt(subscriber.getAreaStrId()));
					subscriber.setAreaId(areaMaster);
				}
				regResult=RegisterService.getInstance().saveSubscriber(appContext,subscriber);
				if(regResult.equalsIgnoreCase("duplicate")){
					model.addAttribute("error", "Sorry, this Mobile number is already registered");
					return "addSubscriber";
				}
				if(regResult.equalsIgnoreCase("error")){
					model.addAttribute("error", "Sorry, failed to add the subscriber");//rare case
					return "addSubscriber";
				}
				if(regResult.equalsIgnoreCase("success")){
					model.addAttribute("message", "Subscriber added successfully.");
					List<User> userList=userService.getAllUserInfo();
					model.addAttribute(userList);
					return "userInfo";
				}
			}
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	@RequestMapping("admin/openEditSubscriber")
	public String openEditSubscriber(Model model,@RequestParam(value = "userId", required = false) String userId){
		try {
			Subscriber subscriber=RegisterService.getInstance().getSubscriberForEdit(appContext,userId);
			if(subscriber.getAreaId()!=null)
			subscriber.setAreaStrId(String.valueOf(subscriber.getAreaId().getId()));
			model.addAttribute("subscriber", subscriber);
			Map<Integer, String> areaMap = paymentService.getAreaMap();
			model.addAttribute("areaMap",areaMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSubscriber";
	}
	@RequestMapping("admin/deleteSubscriber")
	public String deleteSubscriber(Model model,@RequestParam(value = "userId", required = false) String userId){
		try {
			boolean isDeleted=RegisterService.getInstance().deleteSubscriber(appContext,userId);
			List<User> userList=userService.getAllUserInfo();
			model.addAttribute(userList);
			if(isDeleted){
				model.addAttribute("message","Suscriber deleted Successfully");
			}else{
				model.addAttribute("error","Fialed to delete Suscriber");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "userInfo";
	}
	@RequestMapping("admin/openAreaMaster")
	public String listAllAreas(Model model){
		try {
			List<AreaMasterDto> areaList=userService.listAllAreas();
			model.addAttribute("areaList", areaList);
			AreaMasterDto dto=new AreaMasterDto();
			model.addAttribute("areaMasterDto", dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "areaMaster";
	}
	@RequestMapping("admin/areaMaster/add")
	public String addPaymentDef(Model model,@Valid @ModelAttribute("areaMasterDto") AreaMasterDto areaMasterDto,BindingResult result){
		try {
			if(!result.hasErrors()){
				boolean isDuplicate=false;
				if(areaMasterDto.getId()==0){
					isDuplicate=userService.checkForAreaDuplicate(areaMasterDto);			
				}					
				if(!isDuplicate){
					boolean isDefAdded=userService.addOrUpdateArea(areaMasterDto);
					if(isDefAdded){
						model.addAttribute("message","Area added Successfully");
					}else{
						model.addAttribute("error","Fialed to add Area");
					}
				}else{
					model.addAttribute("error","Sorry this entry already exists.");
				}
			}
			
			
			
			List<AreaMasterDto> areaList=userService.listAllAreas();
			model.addAttribute("areaList", areaList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "areaMaster";
	}

	@RequestMapping("admin/areaMaster/edit")
	public String editPaymentDef(Model model,@RequestParam(value = "boId", required = false) String boId){
		try {
			AreaMasterDto areaMasterDto=userService.editArea(boId);
			model.addAttribute("areaMasterDto", areaMasterDto);
			List<AreaMasterDto> areaList=userService.listAllAreas();
			model.addAttribute("areaList", areaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "areaMaster";
	}

	@RequestMapping("admin/areaMaster/delete")
	public String deletePaymentDef(Model model,@ModelAttribute("areaMasterDto") AreaMasterDto areaMasterDto,@RequestParam(value = "boId", required = false) String boId){
		try {
			boolean isPaymentDefDeleted=userService.deletePaymentDef(boId);
			if(isPaymentDefDeleted){
				model.addAttribute("message","Area deleted Successfully");
			}else{
				model.addAttribute("error","Fialed to delete Area");
			}
			List<AreaMasterDto> areaList=userService.listAllAreas();
			model.addAttribute("areaList", areaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "areaMaster";
	}

}
