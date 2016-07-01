package com.ct.person.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ct.person.service.IPersonService;

@Controller
@RequestMapping("admin/person")
public class PersonController {
	
	@Resource	
	private IPersonService personService;
	
	@RequestMapping("index")
	public String index(){
		return "person/person_index";
	}
}
