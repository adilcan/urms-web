package com.gitlab.esenbogagnu.urmsweb.controller;

import com.gitlab.esenbogagnu.urmsweb.domain.User;
import com.gitlab.esenbogagnu.urmsweb.repository.DepartmentRepository;
import com.gitlab.esenbogagnu.urmsweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Controller
@RequestMapping("")
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("")
	public String getDashboard() {
		return "dashboard";
	}

	@GetMapping("/login")
	public String login() {
		return "users/login";
	}

	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("departments", departmentRepository.findAll());
		return "users/register";
	}

	@PostMapping("/register")
	public String postRegister(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("departments", departmentRepository.findAll());
			return "users/register";
		}
		else {
			userService.save(user);
			return "redirect:/login";
		}
	}
}
