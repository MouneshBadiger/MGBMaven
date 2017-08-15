package com.mgb.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

import com.mgb.bo.PaymentDetails;
import com.mgb.forms.Subscriber;
import com.mgb.forms.User;
import com.mgb.services.RegisterService;
import com.mgb.services.UserService;

@Controller()
public class AdminController {
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	UserService userService;
	@RequestMapping("/admin/userInfo")
	public String getAllUsersInfo(Model model){
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
				return "userInfo";
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
		
		Subscriber subscriber=new Subscriber();
		model.addAttribute("subscriber", subscriber);
		return "addSubscriber";
	}
	@RequestMapping("admin/saveSubscriber")
	public String registration(@Valid @ModelAttribute("subscriber") Subscriber subscriber, BindingResult result,Model model) throws Exception{
		String regResult="";
		try{
			
			model.addAttribute("subscriber", subscriber);
			if(result.hasErrors()){
				return "addSubscriber";
			}else{
				//register as user
				subscriber.setRole("ROLE_USER");
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
			model.addAttribute("subscriber", subscriber);
			
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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	










	
	

}
