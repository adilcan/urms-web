package com.gitlab.esenbogagnu.urmsweb.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
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
@Table(name = "urms_user")
public class User extends BaseEntity implements UserDetails {

	@NotEmpty
	private String username;

	@NotEmpty
	@Size(min = 8)
	private String password;

	@NotEmpty
	private String email;

	@NotEmpty
	private String name;

	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	private boolean isExpired = false;

	private boolean isLocked = false;

	private boolean isCredentialsExpired = false;

	private boolean isEnabled = true;

	@ManyToOne
	private Department department;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(getRole().toString());
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(simpleGrantedAuthority);
		return list;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
