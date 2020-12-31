package com.spring.mvc.security.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.mvc.security.dao.PersonDao;
import com.spring.mvc.security.pojo.Person;

public class MongoUserDetailsService implements UserDetailsService{
	
	private boolean accountNonExpired = false;
	private boolean credentialsNonExpired = false;
	private boolean accountNonLocked = false;
	
	PersonDao pDao = new PersonDao();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person user = pDao.getPerson(username);
		
		if(user==null) {
			return null;
		}
		if(user.isEnabled()) {
			accountNonExpired = true;
			credentialsNonExpired = true;
			accountNonLocked = true;
		}
		System.out.println(user.getRole());
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
				accountNonExpired,credentialsNonExpired,accountNonLocked,getAuthorities());
	}
	
	private List<SimpleGrantedAuthority> getAuthorities(){
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authList;
	}

}
