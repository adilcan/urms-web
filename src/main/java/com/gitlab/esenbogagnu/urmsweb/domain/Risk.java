package com.gitlab.esenbogagnu.urmsweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

	@Lob
	private String notes;

	@Enumerated(EnumType.STRING)
	private RiskState state = RiskState.REPORTED;

	@Enumerated(EnumType.STRING)
	private RiskImpact impact;

	@Enumerated(EnumType.STRING)
	private RiskLikelihood likelihood;

	@Enumerated(EnumType.STRING)
	private RiskCategory category;

}
