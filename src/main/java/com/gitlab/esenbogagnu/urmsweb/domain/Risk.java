package com.gitlab.esenbogagnu.urmsweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Risk extends BaseEntity {

	private String subject;

	@JsonIgnore
	@ManyToMany
	private List<Department> departments;

	@Lob
	private String assessment;

	@JsonIgnore
	@Lob
	private String notes;

	@JsonIgnore
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
