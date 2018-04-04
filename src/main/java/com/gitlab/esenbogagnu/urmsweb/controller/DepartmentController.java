package com.gitlab.esenbogagnu.urmsweb.controller;

import com.gitlab.esenbogagnu.urmsweb.domain.Department;
import com.gitlab.esenbogagnu.urmsweb.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping("")
	public String listDepartments(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		return "departments/list";
	}

	@GetMapping("/new")
	public String getNewDepartment(Model model) {
		model.addAttribute("department", new Department());
		return "departments/new";
	}

	@PostMapping("/new")
	public String postNewDepartment(@ModelAttribute @Valid Department department, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "departments/new";
		}
		else {
			departmentRepository.save(department);
			return "redirect:/departments";
		}
	}

	@GetMapping("/{id}/update")
	public String getUpdateDepartment(@PathVariable Long id, Model model) {
		Optional<Department> optionalDepartment = departmentRepository.findById(id);

		if (!optionalDepartment.isPresent()) {
			log.warn("Department with {} ID is not present", id);
			return "redirect:/departments";
		}

		else {
			model.addAttribute("department", optionalDepartment.get());
			return "departments/update";
		}
	}

	@PostMapping("/{id}/update")
	public String postUpdateDepartment(@ModelAttribute @Valid Department department, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "departments/update";
		}
		else {
			departmentRepository.save(department);
			return "redirect:/departments";
		}
	}

	@DeleteMapping("/{id}/delete")
	public String deleteDepartment(@PathVariable Long id) {
		Optional<Department> optionalDepartment = departmentRepository.findById(id);

		if (!optionalDepartment.isPresent()) {
			log.warn("Department with {} ID is not present", id);
		}

		else {
			departmentRepository.delete(optionalDepartment.get());
		}
		return "redirect:/departments";
	}
}
