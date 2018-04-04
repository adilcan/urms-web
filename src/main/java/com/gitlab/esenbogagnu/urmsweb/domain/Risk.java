package com.gitlab.esenbogagnu.urmsweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Risk extends BaseEntity {

	private String subject;

	private RiskImpact impact;

	private RiskLikelihood likelihood;

	@ManyToMany
	private List<Department> departments;

	@ManyToMany
	private List<RiskCategory> categories;

	@Lob
	private String assessment;

	@Lob
	private String notes;

	private MultipartFile file;

	private RiskState state = RiskState.REPORTED;
}
