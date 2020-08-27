package com.tvdinh.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller(value = "homeControllerOfAdmin")
public class HomeController {
	@RequestMapping(value = "/admin-home", method = RequestMethod.GET)
	public String homePage() {

		return "Xin chao cac ban!!";
	}
}
