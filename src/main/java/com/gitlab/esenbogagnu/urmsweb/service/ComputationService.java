package com.gitlab.esenbogagnu.urmsweb.service;

import com.gitlab.esenbogagnu.urmsweb.domain.Risk;
import com.gitlab.esenbogagnu.urmsweb.domain.RiskLikelihood;
import com.gitlab.esenbogagnu.urmsweb.repository.RiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Service
public class ComputationService {

	@Autowired
	private RiskRepository riskRepository;

	public List<String> getRiskComputation() {
		List<String> computationMessages = new ArrayList<>();
		List<Risk> risks = riskRepository.findByLikelihood(RiskLikelihood.CERTAIN);
		risks.addAll(riskRepository.findByLikelihood(RiskLikelihood.LIKELY));
		risks.addAll(riskRepository.findByLikelihood(RiskLikelihood.CREDIBLE));

		if (!risks.isEmpty()) {
			for (Risk r : risks) {
				String message = "''" + r.getSubject() + "'' may be occur again with " + getProbabilityByLikelihood(r.getLikelihood()) + " probability.";
				computationMessages.add(message);
			}
		}
		return computationMessages;
	}

	public String getProbabilityByLikelihood(RiskLikelihood riskLikelihood) {
		String probability = "";

		if (riskLikelihood.equals(RiskLikelihood.CERTAIN)) {
			probability = "80-100%";
		}

		else if (riskLikelihood.equals(RiskLikelihood.LIKELY)) {
			probability = "70-79%";
		}

		else if (riskLikelihood.equals(RiskLikelihood.CREDIBLE)) {
			probability = "40-69%";
		}

		return probability;

	}
}
