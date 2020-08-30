package com.tvdinh.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.dao.ICustomerDAO;
import com.tvdinh.dao.IRoleDAO;
import com.tvdinh.dao.impl.CustomerDAO;
import com.tvdinh.dao.impl.RoleDAO;
import com.tvdinh.entity.CustomerEntity;
import com.tvdinh.entity.RoleEntity;
import com.tvdinh.service.impl.RoleService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public String homePage() {
		
		/*
		com.tvdinh.entity.RoleEntity role=new com.tvdinh.entity.RoleEntity();
		//role.setCode("ADMIN");
		//role.setName("admin");
		role.setCode("USER");
		role.setName("user");
		roleService.save(role);
		*/
		return "hello";
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied() {
		
		return "access Denied";
	}
	
}
