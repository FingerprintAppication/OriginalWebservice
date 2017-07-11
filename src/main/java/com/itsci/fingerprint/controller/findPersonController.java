package com.itsci.fingerprint.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.PersonManager;
import com.itsci.fingerprint.model.Person;




@RestController
public class findPersonController {
	PersonManager psg = new PersonManager();
	List<Person> person = new ArrayList<Person>();
	Map<String,List<Person>> map = new HashMap<String,List<Person>>();
	
	@RequestMapping(value= "/person",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody /*Map<String,List<Person>>*/Person gets(){
		person =psg.getHibernatePerson("7");
		map.put("person", person);
		return psg.getHibernatePerson("7").get(0);
	}
	
	@RequestMapping(value= "/persons",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public /*@ResponseBody*/ String get(){
		
		return "IN NERRRRR";
	}
}
