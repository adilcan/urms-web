package com.gitlab.esenbogagnu.urmsweb;

import com.gitlab.esenbogagnu.urmsweb.domain.*;
import com.gitlab.esenbogagnu.urmsweb.repository.DepartmentRepository;
import com.gitlab.esenbogagnu.urmsweb.repository.RiskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Component
@AllArgsConstructor
public class SetupInitialDataForDev implements CommandLineRunner {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private RiskRepository riskRepository;

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		Department department0 = Department.builder()
				.name("Finance")
				.build();

		departmentRepository.save(department0);

		Department department1 = Department.builder()
				.name("Human Resources")
				.build();

		departmentRepository.save(department1);

		Department department2 = Department.builder()
				.name("External Affairs")
				.build();

		departmentRepository.save(department2);

		Department department3 = Department.builder()
				.name("Information Technologies")
				.build();

		departmentRepository.save(department3);

		Department department4 = Department.builder()
				.name("Health")
				.build();

		departmentRepository.save(department4);

		Risk risk0 = Risk.builder()
				.subject("Shortage of working capital")
				.assessment("There is an ongoing shortage for determined project.")
				.state(RiskState.REPORTED)
				.impact(RiskImpact.MAJOR)
				.likelihood(RiskLikelihood.LIKELY)
				.category(RiskCategory.FINANCIAL)
				.departments(Arrays.asList(department0))
				.build();

		riskRepository.save(risk0);

		Risk risk1 = Risk.builder()
				.subject("Key personnel have inadequate authority to fulfil roles.")
				.assessment("Key personnel have inadequate authority to fulfil roles.")
				.state(RiskState.REPORTED)
				.impact(RiskImpact.INSIGNIFICANT)
				.likelihood(RiskLikelihood.CREDIBLE)
				.category(RiskCategory.ORGANIZATIONAL)
				.departments(Arrays.asList(department1))
				.build();

		riskRepository.save(risk1);

		Risk risk2 = Risk.builder()
				.subject("Many bugs and exploits detected on Student Information System.")
				.assessment("There are several bugs and CVE-321 exploits on SIS.")
				.state(RiskState.REPORTED)
				.impact(RiskImpact.EXTREME)
				.likelihood(RiskLikelihood.REMOTE)
				.category(RiskCategory.TECHNICAL)
				.departments(Arrays.asList(department3))
				.build();

		riskRepository.save(risk2);

		Risk risk3 = Risk.builder()
				.subject("Failure to meet project revenue targets.")
				.assessment("Failure to meet project revenue targets.")
				.state(RiskState.REPORTED)
				.impact(RiskImpact.MODERATE)
				.likelihood(RiskLikelihood.CERTAIN)
				.category(RiskCategory.FINANCIAL)
				.departments(Arrays.asList(department1))
				.build();

		riskRepository.save(risk3);

		Risk risk4 = Risk.builder()
				.subject("Many student poisonings reported.")
				.assessment("There are poisoned students and claimed they eat school food.")
				.state(RiskState.REPORTED)
				.impact(RiskImpact.MAJOR)
				.likelihood(RiskLikelihood.UNLIKELY)
				.category(RiskCategory.PEOPLE)
				.departments(Arrays.asList(department4))
				.build();

		riskRepository.save(risk4);
	}
}
