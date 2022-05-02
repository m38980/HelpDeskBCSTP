package com.bcstp.helpdesk.services;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bcstp.helpdesk.models.Student;
import com.bcstp.helpdesk.models.User;
import com.bcstp.helpdesk.repositories.StudentRepository;
import com.bcstp.helpdeskcom.bcstp.helpdesk.util.StudentUtil;

	

	@Service
	public class StudentService {
		
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> listAll(String keyword) {
		if (keyword != null) {
			return studentRepository.search(keyword);
		}
		return (List<Student>) studentRepository.findAll();
	}
	
	/* BEGIN paginação */
	
	public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return studentRepository.findAll(pageable);
	}
	
	/* END paginação */ 
	
	public Optional<Student> getOne(Integer Id) {
		return studentRepository.findById(Id);
	}

	public void addNew(Student student) {
		studentRepository.save(student);
	}
	
	public void update(Student student) {
		studentRepository.save(student);
	}
	
	public void delete(Integer Id) {
		studentRepository.deleteById(Id);
	}
	
	public List<Student> listPdfStudent() {
        return (List<Student>) studentRepository.findAll();
    }
	
	

}
