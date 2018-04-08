package com.gitlab.esenbogagnu.urmsweb.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
public class Department extends BaseEntity {

	@NotEmpty
	private String name;

	@Email
	@NotEmpty
	private String email;

	@OneToMany(mappedBy = "department")
	private List<User> users;

	@ManyToMany
	private List<Risk> risks;

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
