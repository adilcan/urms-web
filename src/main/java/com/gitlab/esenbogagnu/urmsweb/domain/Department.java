package com.gitlab.esenbogagnu.urmsweb.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
}
