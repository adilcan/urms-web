package com.gitlab.esenbogagnu.urmsweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class CustomMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

	@Autowired
	private CustomMethodSecurityExpressionHandler customMethodSecurityExpressionHandler;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		return customMethodSecurityExpressionHandler;
	}

}
