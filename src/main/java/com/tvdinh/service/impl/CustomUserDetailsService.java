package com.tvdinh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tvdinh.converter.CustomerBeanUtil;
import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.entity.CustomerEntity;
import com.tvdinh.repository.CustomerRepository;
import com.tvdinh.security.MyUser;
import com.tvdinh.service.ICustomerService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		//CustomerDTO user=customerService.findOneUserNameAndStatus(username, 1);
		CustomerDTO user=CustomerBeanUtil.entityToDTO(customerRepository.findByUsername(username));
		List<CustomerEntity>list=customerRepository.findAll();
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		
		List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
		for(RoleDTO role: user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		
		//User của spring security đã được custom qua MyUser và do User implements UserDetails nên ta có thể return myUser
		MyUser myUser=new MyUser(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		myUser.setFullName(user.getName());
		return myUser;
	}

}
