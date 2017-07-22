package com.xuchg.mssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuchg.mssm.module.User;
import com.xuchg.mssm.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public String getAll(ModelMap map,HttpServletRequest request){
		List<User> list = userService.getAll();
		map.addAttribute("list",list);
		return "index";
	}
}
