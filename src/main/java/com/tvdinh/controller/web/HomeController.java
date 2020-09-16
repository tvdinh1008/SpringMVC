package com.tvdinh.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.model.CustomerModel;
import com.tvdinh.service.ICustomerService;
import com.tvdinh.service.IRoleService;



@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav=new ModelAndView("web/home");
		
		//com.tvdinh.entity.RoleEntity role=new com.tvdinh.entity.RoleEntity();
		//role.setCode("ADMIN");
		//role.setName("admin");
		//role.setCode("USER");
		//role.setName("user");
		//roleService.save(role);
		
		return mav;
	}
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public ModelAndView registerPage(Model model) {
		ModelAndView mav = new ModelAndView("register");
		model.addAttribute("customerModel", new CustomerModel());
		return mav;
	}
	
	@RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
	public ModelAndView addCustomer(@ModelAttribute("customerModel")CustomerModel customerModel, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView("profile");
		customerModel.setPojo(customerService.save(customerModel.getPojo()));
		if(customerModel.getPojo()!=null) {
			modelMap.addAttribute("customer",customerModel.getPojo());
			return mav;
		}else {
			return new ModelAndView("register");
		}
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
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
	
}
