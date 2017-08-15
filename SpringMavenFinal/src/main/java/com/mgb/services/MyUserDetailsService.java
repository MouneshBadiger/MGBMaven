package com.mgb.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mgb.bo.CustomUserDetails;
import com.mgb.daoImpl.RegistrationDaoImpl;
import com.mgb.daos.IRegistrationDao;
@Component
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private IRegistrationDao dao;
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		com.mgb.forms.User cUser=dao.loadUserByUsername(userName);
		 if(cUser == null) {
	            throw new UsernameNotFoundException("User name not found");
	     }
		 Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 for (int i=0;i<1;i++) {//usually one user can have many role here its restricted to one role only
	            authorities.add(new SimpleGrantedAuthority(cUser.getRole()));
	        }
		

		CustomUserDetails user = new CustomUserDetails (userName, cUser.getPassword(), cUser.getIsActive(), true, true, true, authorities, cUser.getId());
		return user;
		
	}
	
}
