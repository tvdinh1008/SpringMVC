package com.tvdinh.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.service.impl.RoleService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("");
		com.tvdinh.entity.RoleEntity role=new com.tvdinh.entity.RoleEntity();
		//role.setCode("ADMIN");
		//role.setName("admin");
		role.setCode("USER");
		role.setName("user");
		roleService.save(role);
		return mav;
	}
}
