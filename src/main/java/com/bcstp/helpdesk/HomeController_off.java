package com.bcstp.helpdesk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController_off {
	
	@RequestMapping("/home")
	public String test() {
		return "home";
	}

}
