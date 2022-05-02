package com.bcstp.helpdesk.services;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bcstp.helpdesk.models.User;
import com.bcstp.helpdesk.repositories.UserRepository;


@Service
@Transactional
public class UserService {

	// public static final int USER_PER_PAGE = 3;

	@Autowired
	private UserRepository userRepository;

	public User login(String username, String password) {

		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}

	public List<User> listAll(String keyword) {

		if (keyword != null) {
			return userRepository.search(keyword);
		}
		return (List<User>) userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public User get(Long id) {
		return userRepository.findById(id).get();
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public Page<User> listAllPages(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
	    return userRepository.findAll(pageable);
	}
	
	public List<User> listPdfUser() {
        return (List<User>) userRepository.findAll();
    }
	
	public List<User> listAll() {
        return userRepository.findAll(Sort.by("email").ascending());
    }
}
