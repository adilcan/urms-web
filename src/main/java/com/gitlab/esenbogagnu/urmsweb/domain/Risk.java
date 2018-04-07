package com.gitlab.esenbogagnu.urmsweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

	@NotEmpty
	private String subject;

	@NotNull
	@JsonIgnore
	@ManyToMany
	private List<Department> departments;

	@Lob
	@NotEmpty
	private String assessment;

	@Lob
	private String notes;

	@Enumerated(EnumType.STRING)
	private RiskState state = RiskState.REPORTED;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RiskImpact impact;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RiskLikelihood likelihood;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RiskCategory category;

}
