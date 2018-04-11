package com.gitlab.esenbogagnu.urmsweb.controller;

import com.gitlab.esenbogagnu.urmsweb.domain.User;
import com.gitlab.esenbogagnu.urmsweb.domain.UserDto;
import com.gitlab.esenbogagnu.urmsweb.repository.UserRepository;
import com.gitlab.esenbogagnu.urmsweb.service.UserService;
import com.gitlab.esenbogagnu.urmsweb.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

	@Autowired
	private UserService userService;

	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}


	@GetMapping
	private String getUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users/list";
	}

	@GetMapping("/update-password")
	private String getUpdatePassword(Model model) {
		model.addAttribute("user", new UserDto());
		return "users/updatePassword";
	}

	@PostMapping("/update-password")
	private String postUpdatePassword(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, @AuthenticationPrincipal User user, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", new UserDto());
			return "users/updatePassword";
		}
		else {
			userService.updatePassword(userDto, user);
			return "redirect:/users";
		}
	}
}
