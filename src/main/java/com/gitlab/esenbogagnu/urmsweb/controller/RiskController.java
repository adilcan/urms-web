package com.gitlab.esenbogagnu.urmsweb.controller;

import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import com.gitlab.esenbogagnu.urmsweb.repository.DepartmentRepository;
import com.gitlab.esenbogagnu.urmsweb.repository.RiskRepository;
import com.gitlab.esenbogagnu.urmsweb.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Slf4j
@Controller
@RequestMapping("/risks")
public class RiskController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private RiskRepository riskRepository;

	@Autowired
	private EmailService emailService;

	@GetMapping("")
	public String listRisks(Model model, @RequestParam(required = false, name = "resolved") boolean isResolved) {
		if (isResolved) {
			model.addAttribute("risks", riskRepository.findByIsResolved(true));
		}
		else {
			model.addAttribute("risks", riskRepository.findByIsResolved(false));
		}
		return "risks/list";
	}

	@GetMapping("/new")
	public String getNewRisk(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		model.addAttribute("risk", new Risk());
		return "risks/new";
	}

	@PostMapping("/new")
	public String postNewRisk(@ModelAttribute @Valid Risk risk, BindingResult bindingResult) throws MessagingException {
		if (bindingResult.hasErrors()) {
			return "risks/new";
		}
		else {
			riskRepository.save(risk);
			emailService.sendRiskAsMail(risk);
			return "redirect:/risks";
		}
	}

	@GetMapping("/{id}")
	public String showRisk(@PathVariable Long id, Model model) {
		Optional<Risk> optionalRisk = riskRepository.findById(id);

		if (!optionalRisk.isPresent()) {
			log.warn("Risk with {} ID is not present", id);
			return "redirect:/risks";
		}

		else {
			model.addAttribute("risk", optionalRisk.get());
			return "risks/show";
		}
	}

	@GetMapping("/{id}/update")
	public String getUpdateRisk(@PathVariable Long id, Model model) {
		Optional<Risk> optionalRisk = riskRepository.findById(id);

		if (!optionalRisk.isPresent()) {
			log.warn("Risk with {} ID is not present", id);
			return "redirect:/risks";
		}

		else {
			model.addAttribute("departments", departmentRepository.findAll());
			model.addAttribute("risk", optionalRisk.get());
			return "risks/update";
		}
	}

	@PostMapping("/{id}/update")
	public String postUpdateRisk(@ModelAttribute @Valid Risk risk, BindingResult bindingResult, Model model) throws MessagingException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("departments", departmentRepository.findAll());
			return "risks/update";
		}
		else {
			riskRepository.save(risk);
			emailService.sendRiskAsMail(risk);
			return "redirect:/risks/" + risk.getId();
		}
	}

	@DeleteMapping("/{id}/delete")
	public String deleteRisk(@PathVariable Long id) {
		Optional<Risk> optionalRisk = riskRepository.findById(id);

		if (!optionalRisk.isPresent()) {
			log.warn("Risk with {} ID is not present", id);
		}

		else {
			riskRepository.delete(optionalRisk.get());
		}
		return "redirect:/risks";
	}

}
