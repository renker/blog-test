package com.ct.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class UrlAuthorizationFilter extends AuthorizationFilter{

	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)throws Exception {
		return false;
	}
	
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)throws Exception {
		return super.onAccessDenied(request, response, mappedValue);
	}

}
