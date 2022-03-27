package com.example.assignment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> listcustomers = service.listAll();
		model.addAttribute("listcustomers",listcustomers);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewcustomerPage(Model model) {
	    Customer customer = new Customer();
	    model.addAttribute("customer", customer);
	    return "new_customer";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savecustomer(@ModelAttribute("customer") Customer customer) {
	    service.save(customer);
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditcustomerPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_customer");
	    Customer customer = service.get(id);
	    mav.addObject("customer", customer);
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deletecustomer(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
}
