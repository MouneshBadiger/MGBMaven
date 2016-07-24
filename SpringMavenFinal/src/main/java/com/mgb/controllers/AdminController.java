package com.mgb.controllers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mgb.forms.User;
import com.mgb.services.UserService;

@Controller()
public class AdminController {
	//ApplicationContext appContex;
	@Autowired
	UserService userService;
	@RequestMapping("/admin/userInfo")
	public String getAllUsersInfo(Model model){
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
			System.out.println(userList);
			model.addAttribute(userList);
			return "userInfo";
		}else{
			model.addAttribute("error","Sorry, seems like you do not have access.");
			return "error";
		}
		
		
		
	}
	@RequestMapping("/admin")
	public String welcomeAdmin(Model model){
		return "adminPage";
	}
	@RequestMapping("/admin/sendRequest")
	public String sendRequest(Model model){
		String USER_AGENT = "Mozilla/5.0";
		try{
		String url = "http://localhost:8080/SpringPro/getResponse";
				URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
                                    
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		model.addAttribute("msg", response.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}











	
	

}
