package com.gitlab.esenbogagnu.urmsweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Configuration
@EnableJpaAuditing
public class AuditConfig {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

}
