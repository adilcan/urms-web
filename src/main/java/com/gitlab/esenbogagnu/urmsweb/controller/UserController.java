package com.gitlab.esenbogagnu.urmsweb.controller;

import com.gitlab.esenbogagnu.urmsweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	private String getUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users/list.html";
	}
}
