package com.example.Kirjakauppis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Kirjakauppis.domain.User;
import com.example.Kirjakauppis.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository rep;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository usrRep) {
		this.rep = usrRep;
	}
	
	@Override
	public UserDetails loadUserByUsername(String usrName) throws UsernameNotFoundException {
		User currUsr = rep.findByUsername(usrName);
		UserDetails user = new org.springframework.security.core.userdetails.User(usrName, 
				currUsr.getPasswordHash(),
				AuthorityUtils.createAuthorityList(currUsr.getRole()));
		return user;
	}
}
