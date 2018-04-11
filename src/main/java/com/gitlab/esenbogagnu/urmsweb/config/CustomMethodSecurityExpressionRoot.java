package com.gitlab.esenbogagnu.urmsweb.config;

import com.gitlab.esenbogagnu.urmsweb.domain.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created on April, 2018
 *
 * @author adilcan
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	private Object filterObject;

	private Object returnObject;

	private Object target;

	CustomMethodSecurityExpressionRoot(Authentication a) {
		super(a);
	}

	public User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	CustomMethodSecurityExpressionRoot setTarget(Object target) {
		this.target = target;
		return this;
	}

	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	@Override
	public Object getFilterObject() {
		return filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	@Override
	public Object getReturnObject() {
		return returnObject;
	}

	@Override
	public Object getThis() {
		return target;
	}
}
