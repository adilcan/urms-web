package com.gitlab.esenbogagnu.urmsweb.controller;

import com.gitlab.esenbogagnu.urmsweb.domain.User;
import com.gitlab.esenbogagnu.urmsweb.domain.UserDto;
import com.gitlab.esenbogagnu.urmsweb.repository.DepartmentRepository;
import com.gitlab.esenbogagnu.urmsweb.repository.UserRepository;
import com.gitlab.esenbogagnu.urmsweb.service.UserService;
import com.gitlab.esenbogagnu.urmsweb.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentRepository departmentRepository;

	@InitBinder("users/updatePassword")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	@GetMapping
	public String getUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users/list";
	}

	@GetMapping("/update-password")
	public String getUpdatePassword(Model model) {
		model.addAttribute("user", new UserDto());
		return "users/updatePassword";
	}

	@PostMapping("/update-password")
	public String postUpdatePassword(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, @AuthenticationPrincipal User user,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", new UserDto());
			return "users/updatePassword";
		}
		else {
			userService.updatePassword(userDto, user);
			return "redirect:/users";
		}
	}

	@GetMapping("/{id}/update")
	public String getUpdateUser(@PathVariable Long id, Model model) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (!optionalUser.isPresent()) {
			log.warn("User with {} ID is not present", id);
			return "redirect:/users";
		}

		else {
			model.addAttribute("departments", departmentRepository.findAll());
			model.addAttribute("user", optionalUser.get());
			return "users/update";
		}
	}

	@PostMapping("/{id}/update")
	public String postUpdateUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departmentRepository.findAll());
			model.addAttribute("user", user);

			return "users/update";
		}
		else {
			userService.save(user);
			return "redirect:/users/";
		}
	}
}
