package com.mgb.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mgb.forms.User;
import com.mgb.services.RegisterService;

@Controller("/SpringMaven/")
public class WelcomeController {
	@Autowired
	private ApplicationContext appContext;
	@RequestMapping("/home")
	public String openWelcomePage(){
		//TestClass testClass=(TestClass) appContext.getBean("testClass");
		return "welcome";
	}
	@RequestMapping("/about")
	public String openAboutDetails(){
		return "about";
	}
	@RequestMapping("/register")
	public String openRegisterPage(Model model){
		
		//return new ModelAndView("register", "command", new Register());
		User prePop=new User();
		//prePop.setName("Mounesh");
		/*prePop.setEmail("mounesh.gb@gmail.com");
		prePop.setDob(new Date());
		prePop.setAddress("Post:Kenganur");
		prePop.setMobileNo("7353207901");*/
		model.addAttribute("register", prePop);
		return "register";
	}
	@RequestMapping("/registration")
	public String registration(@Valid @ModelAttribute("register") User register, BindingResult result,Model model) throws Exception{
		String regResult="";
		try{
			/*for(MultipartFile f:file2){
				System.out.println(f.getSize());
				File serverFile=new File("D:/files/"+f.getOriginalFilename());
				 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				 stream.write(f.getBytes());
				 stream.close();
			}*/
			
			model.addAttribute("register", register);
			//model.addAttribute("error", "This is general the error message");
			if(!result.hasErrors() && !register.getRePassword().equals(register.getPassword())){
				result.rejectValue("rePassword", "register.rePassword","both pass should be same");
			}
			if(result.hasErrors()){
				return "register";
			}else{
				//register as user
				register.setRole("ROLE_USER");
				regResult=RegisterService.getInstance().rigisterUser(appContext,register);
				if(regResult.equalsIgnoreCase("duplicate")){
					model.addAttribute("error", "Sorry, this email id is already registered");
					return "register";
				}
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return regResult;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "username", required = false) String username,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		if (error != null ) {
			model.addAttribute("error", "Invalid username and password!");
		}
		return "login";

	}
	@RequestMapping("/loginSuccess")
	public String loginSuccess(Model model,HttpServletRequest request){
		
		/*   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		      String username = auth.getName(); //get logged in username
		      model.addAttribute("username", username);
		HttpSession session=request.getSession(false);
		session.setAttribute("ses_user", username);*/
		return "loginSuccess";	}
	 @RequestMapping(value="/logout", method = RequestMethod.GET)
	    public String logoutPage (HttpServletRequest request, HttpServletResponse response,Model model) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null){    
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	            model.addAttribute("msg","You have successfully loged out.");
	            
	        }
	        return "login";
	    }
	 @RequestMapping("/editProfile")
	 public String editProfile(){
		 return "editProfile";
	 }

}
