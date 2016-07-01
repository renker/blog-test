package com.ct.person.service;
import com.ct.person.entity.Person;

public interface IPersonService {
	Person login(String account);
		
	Person login(String account,String password);
	
	Person selectById(String id);
}
