package com.bcstp.helpdesk.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcstp.helpdesk.StudentPDFExporter;
import com.bcstp.helpdesk.UserPDFExporter;
import com.bcstp.helpdesk.models.Student;
import com.bcstp.helpdesk.models.User;
import com.bcstp.helpdesk.repositories.StudentRepository;
import com.bcstp.helpdesk.services.StudentService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@RequestMapping("/getAll")
	@GetMapping()
	public String getAll(Model model, @Param("keyword") String keyword) {
		List<Student> students = studentService.listAll(keyword);
		
		model.addAttribute("students", students);
		model.addAttribute("keyword", keyword);
		
		return findPaginated(1, "name", "asc", model);
		
		//String username = "Filipe";
		//model.addAttribute("username", roles);
		
		//return "students";
	}
	
	/*
	@GetMapping("/")
	public String getAllPages(Model model){
	    return getOnePage(model, 1);
	}
	*/
	/*  BEGIN PAGINATION GENERATED */
	/*
	@GetMapping("/students/getAll/{pageNumber}")
	public  String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
		Page<Student> page = studentService.findPage(currentPage);
		
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		List<Student> students = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("totalItems", totalItems);
	    model.addAttribute("students", students);
		
		return "redirect:/students/getAll";
	}
	*/
	
	
	
	@GetMapping("/getAll/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> students = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("students", students);
		return "students";
	}
	/*  END PAGINATION GENERATED */
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Student> getOne(Model model, Integer Id) {
		//model.addAttribute("Student", Student);
		return studentService.getOne(Id);
	}
	
	@PostMapping("/addNew")
	public String addNew(Student student) {
		studentService.addNew(student);
		return "redirect:/students/getAll";
	}
	
	
	@RequestMapping(value="/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Student student) {
		studentService.update(student);
		return "redirect:/students/getAll";		
	}
	
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer Id) {
		studentService.delete(Id);
		return "redirect:/students/getAll";		
	}
	
	/*  BEGIN PDF GENERATED */
	
	@GetMapping("/students/export/pdf")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=students_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<Student> listStudents = studentService.listPdfStudent();

		StudentPDFExporter exporter = new StudentPDFExporter(listStudents);
		exporter.export(response);

	}
	
	/*  END PDF GENERATED */
}
