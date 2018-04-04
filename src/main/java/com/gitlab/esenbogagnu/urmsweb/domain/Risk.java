package com.gitlab.esenbogagnu.urmsweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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

	@ManyToMany
	private List<Department> departments;

	@Lob
	private String assessment;

	@Lob
	private String notes;

	@Transient
	private MultipartFile file;

	@Enumerated(EnumType.STRING)
	private RiskState state = RiskState.REPORTED;

	@Enumerated(EnumType.STRING)
	private RiskImpact impact;

	@Enumerated(EnumType.STRING)
	private RiskLikelihood likelihood;

	@Enumerated(EnumType.STRING)
	private RiskCategory category;

}
