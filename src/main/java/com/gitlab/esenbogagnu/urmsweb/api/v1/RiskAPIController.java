package com.gitlab.esenbogagnu.urmsweb.api.v1;

import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import com.gitlab.esenbogagnu.urmsweb.repository.RiskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/risks")
public class RiskAPIController {

	@Autowired
	private RiskRepository riskRepository;

	@GetMapping("")
	public ResponseEntity<Iterable<Risk>> listRisks() {
		Iterable<Risk> risks = riskRepository.findAll();

		return ResponseEntity.ok(risks);
	}

	@PostMapping("")
	public ResponseEntity<Risk> newRisk(@Validated @RequestBody Risk risk) {

		riskRepository.save(risk);

		return ResponseEntity.ok(risk);

	}

	@PutMapping("")
	public ResponseEntity<Risk> updateRisk(@Validated @RequestBody Risk risk) {

		Risk savedRisk = riskRepository.save(risk);

		return ResponseEntity.ok(savedRisk);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Risk> getRiskById(@PathVariable("id") Long id) {

		Optional<Risk> optionalRisk = riskRepository.findById(id);

		if (!optionalRisk.isPresent()) {
			log.warn("Risk with {} ID is not present.", id);
			return ResponseEntity.notFound().build();
		}

		else {
			return ResponseEntity.ok(optionalRisk.get());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRiskById(@PathVariable("id") Long id) {

		riskRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<Iterable<Risk>> searchRisk(@RequestParam(value = "subject") String subject) {
		List<Risk> risks = riskRepository.findBySubject(subject);

		return ResponseEntity.ok(risks);
	}

}
