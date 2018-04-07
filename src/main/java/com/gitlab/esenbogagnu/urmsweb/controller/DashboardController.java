package com.gitlab.esenbogagnu.urmsweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Controller
@RequestMapping("")
public class DashboardController {

	@GetMapping("")
	public String getDashboard() {
		return "dashboard";
	}
}
