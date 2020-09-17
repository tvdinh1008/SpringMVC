package com.tvdinh.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tvdinh.dto.CustomerDTO;
import com.tvdinh.dto.RoleDTO;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.service.ICustomerService;
import com.tvdinh.service.IRoleService;
import com.tvdinh.util.MessageUtil;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IRoleService roleServie;
	@Autowired
	private MessageUtil messageUtil;
	
	
	@RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav=new ModelAndView("admin/home");
		return mav;
	}
	
	/*
	 * Sử dụng request khi ở trang list mà Admin muốn xóa thì sau khi xóa nó sẽ trả về message thông báo thành công hay thất bại
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/quan-tri/customer/list", method = RequestMethod.GET)
	public ModelAndView listUser(@ModelAttribute("model")CustomerModel model,ModelMap modelMap, HttpServletRequest request) {
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
		//property.put("username", "adm");
		//property.put("status", 1);
		//Khi người dùng tìm kiếm username(dựa vào input search)
		if(StringUtils.isNotBlank(model.getPojo().getUsername())){
			property.put("username", model.getPojo().getUsername());
		}
		
		Integer offset=(model.getCurrentPage()-1)*model.getMaxPageItem();
		Integer limit=model.getMaxPageItem();
		String sortExpression="id";
		String sortDirection="DESC";
		Object[] objects=customerService.findByProperties(property, offset, limit, sortExpression, sortDirection);
		
		model.setListResult((List<CustomerDTO>) objects[1]);
		model.setTotalItem( (Long) objects[0]);
		model.setTotalPage((int)Math.ceil((double)model.getTotalItem()/model.getMaxPageItem()));
		
		modelMap.addAttribute("model", model);
		
		if(request.getParameter("message")!=null) {
			Map<String,String> message=messageUtil.getMessage(request.getParameter("message"));
			modelMap.addAttribute("message", message.get("message"));
			modelMap.addAttribute("alert",message.get("alert"));
		}
		
		return mav;
	}
	@RequestMapping(value = "/quan-tri/customer/edit", method=RequestMethod.GET)
	public ModelAndView editUser(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav=new ModelAndView("admin/customer/edit");
		CustomerModel model=new CustomerModel();
		if(id!=null)
		{
			model.setPojo(customerService.findById(id));
			String[] roleCode=new String[model.getPojo().getRoles().size()];
			int i=0;
			for(RoleDTO role: model.getPojo().getRoles()) {
				roleCode[i]=role.getCode();
				i++;
			}
			model.setRoleCode(roleCode);
		}
		//Khi click insert hoặc update thì nó sẽ redirect tới đây và dựa vào message để đẩy ra thông báo
		if(request.getParameter("message")!=null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		
		//Danh sách trạng thái
		Map<Integer, String> statuses=new HashMap<Integer, String>();
		statuses.put(1, "Bình thường");
		statuses.put(2,"Chặn");
		
		mav.addObject("statuses", statuses);
		mav.addObject("model", model);
		//danh sách quyền
		mav.addObject("roles", roleServie.findAllMap());
		return mav;
	}
}
