package com.gitlab.esenbogagnu.urmsweb.config;

import com.gitlab.esenbogagnu.urmsweb.domain.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		//TODO Following condition just used to bypass auditing exception on SetupInitialData, should be after SetupInitialData deprecated.

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return  Optional.of("SetupInitialData");
		}
		else {
			return Optional.of(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		}
	}
}

