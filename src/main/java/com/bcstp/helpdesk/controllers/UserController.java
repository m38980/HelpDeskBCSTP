package com.bcstp.helpdesk.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bcstp.helpdesk.UserExcelExporter;
import com.bcstp.helpdesk.UserPDFExporter;
import com.bcstp.helpdesk.models.User;
import com.bcstp.helpdesk.repositories.UserRepository;
import com.bcstp.helpdesk.services.UserService;
import com.lowagie.text.DocumentException;


@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping()
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<User> listUsers = userService.listAll(keyword);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("keyword", keyword);

		return listByPage(model, 1);
	}

	@GetMapping("/users/{pageNumber}")
	public String listByPage(Model model, @Param("pageNumber") int curentPage) {

		Page<User> page = userService.listAllPages(curentPage);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<User> listUsers = page.getContent();

		model.addAttribute("curentPage", curentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("listUsersPages", listUsers);

		return "users";

	}

	@GetMapping("/novo")
	public String showNewUserPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "novo_user";
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		userService.save(user);

		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditUserPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("editar_user");
		User user = userRepository.findById(id).orElse(null);

		mav.addObject("user", user);

		return mav;
	}

	@PostMapping("/update/{id}")
	public String updateUser(@ModelAttribute("user") User user, @PathVariable(name = "id") Long id) {
		User userActual = userRepository.findById(id).orElse(null);
		if (userActual != null) {

			BeanUtils.copyProperties(user, userActual, "user_id");
			userService.save(userActual);

		}

		return "redirect:/users";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Long id) {
		userService.delete(id);

		return "redirect:/users";

	}

	@GetMapping("/users/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<User> listUsers = userService.listPdfUser();

		UserPDFExporter exporter = new UserPDFExporter(listUsers);
		exporter.export(response);

	}
	/*
	@RequestMapping(value= "/api/**", method=RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");
	}
	
	*/
	
	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<User> listUsers = userService.listAll();

		UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

		excelExporter.export(response);
	}
	/*
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<User> page = iUserService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<User> listUsers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
*/
}
