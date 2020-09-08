package com.tvdinh.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.service.ICustomerService;
import com.tvdinh.service.impl.CustomerService;


@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	
	@Autowired
	private ICustomerService customerService;
	
	
	@RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav=new ModelAndView("admin/home");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/quan-tri/customer/list", method = RequestMethod.GET)
	public ModelAndView listUser(@ModelAttribute("model")CustomerModel model,ModelMap modelMap) {
		ModelAndView mav=new ModelAndView("admin/customer/list");
		
		/*
		 * model lấy được page đang đứng và limit
		 * truy vấn cần offset và limit
		 */
		if(model.getMaxPageItem()==null && model.getCurrentPage()==null)
		{
			model.setMaxPageItem(2);
			model.setCurrentPage(1);
		}
		Map<String, Object> property=new HashMap<String, Object>();
		property.put("username", "dinh");
		property.put("status", 1);
		Integer offset=(model.getCurrentPage()-1)*model.getMaxPageItem();
		Integer limit=model.getMaxPageItem();
		String sortExpression="id";
		String sortDirection="DESC";
		Object[] objects=customerService.findByProperties(property, offset, limit, sortExpression, sortDirection);
		

		model.setListResult((List<CustomerDTO>) objects[1]);
		model.setTotalItem( (Long) objects[0]);
		model.setTotalPage((int)Math.ceil((double)model.getTotalItem()/model.getMaxPageItem()));
		
		
		modelMap.addAttribute("model", model);
		return mav;
	}
}
