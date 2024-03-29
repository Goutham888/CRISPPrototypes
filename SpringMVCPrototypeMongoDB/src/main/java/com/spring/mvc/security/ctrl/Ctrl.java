package com.spring.mvc.security.ctrl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.security.dao.PersonDao;
import com.spring.mvc.security.dao.RecordsDao;
import com.spring.mvc.security.pojo.StoreRecord;

@Controller
public class Ctrl {
	
	@Autowired
	RecordsDao records;
	
	
	// If user will be successfully authenticated he/she will be taken to the login secure page.
	@RequestMapping(value="/stores", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView m = new ModelAndView();
		m.setViewName("stores");

		return m;
		
	}

	// Spring security will see this message.
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView m = new ModelAndView();
		if (error != null) {
			m.addObject("error", "Invalid username and password error.");
		}

		if (logout != null) {
			m.addObject("msg", " You have left successfully.");
		}
		m.setViewName("login");
		return m;
	}

	@RequestMapping(value="/productQuery", method=RequestMethod.POST)
	public ModelAndView giveResults(@RequestParam(value="product", required=true) String product, 
			@RequestParam(value = "store", required = true) String store) {
		ArrayList<StoreRecord> results=records.retrieveRecords(store, product);
		
		ModelAndView m = new ModelAndView();
		m.addObject("results",results);
		m.setViewName("results");

		return m;
	}
}