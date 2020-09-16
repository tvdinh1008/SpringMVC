package com.tvdinh.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tvdinh.dto.RoleDTO;
import com.tvdinh.model.CustomerModel;
import com.tvdinh.service.ICustomerService;
import com.tvdinh.service.IRoleService;

@RestController(value = "customerAPIOfAdmin")
public class CustomerAPI {
	
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IRoleService roleService;
	 
	@PostMapping("/api/customer")
	public CustomerModel createCustomer(@RequestBody CustomerModel model) {
		for(String code:model.getRoleCode()) {
			RoleDTO role=roleService.findByCode(code);
			if(role!=null) {
				model.getPojo().getRoles().add(role);
			}
		}
		model.setPojo(customerService.save(model.getPojo()));
		return model;
	}
	@PutMapping("/api/customer")
	public CustomerModel updateCustomer(@RequestBody CustomerModel model) {
		for(String code:model.getRoleCode()) {
			RoleDTO role=roleService.findByCode(code);
			if(role!=null) {
				model.getPojo().getRoles().add(role);
			}
		}
		model.setPojo(customerService.save(model.getPojo()));
		return model;
	}
	@DeleteMapping("/api/customer")
	public Boolean deleteCustomer(@RequestBody Long[] ids) {
		return customerService.deleteById(ids);
	}
}
