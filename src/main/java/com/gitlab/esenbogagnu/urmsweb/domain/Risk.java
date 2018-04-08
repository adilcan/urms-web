package com.gitlab.esenbogagnu.urmsweb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
@EntityListeners(AuditingEntityListener.class)
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

	private boolean isResolved = false;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:SS")
	private LocalDate creationDate;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:SS")
	private LocalDate modifyDate;

	@LastModifiedBy
	private String modifiedBy;

}
