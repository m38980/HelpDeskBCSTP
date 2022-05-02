package com.bcstp.helpdesk.controllers;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bcstp.helpdesk.models.User;
import com.bcstp.helpdesk.services.StudentService;
import com.bcstp.helpdesk.services.UserService;

@SuppressWarnings("unused")
@Controller
@RequestMapping
public class AppController {

	@SuppressWarnings("unused")
	@Autowired
	private StudentService studentService;

	private UserService userService;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("user", new User());
		return mav;

	}

	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user) {

		@SuppressWarnings("unused")
		User loginUser = userService.login(user.getUsername(), user.getPassword());

		System.out.print(user);
		if (Objects.nonNull(user)) {

			return "redirect:/students";

		} else {
			return "redirect:/login";

		}

	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {

		return "redirect:/login";
	}

	@RequestMapping("/")
	public String paginaPrincipal() {
		return "home";

	}

	@GetMapping("/403")
	public String error403() {
		return "403";

	}

}
