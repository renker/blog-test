package com.ct.shiro.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class RolesAuthorizationFilter extends AuthorizationFilter{

	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		
		Subject subject = getSubject(request, response);
		String rolesArray[] = (String[])(String[])mappedValue;
		
		if (rolesArray == null || rolesArray.length == 0) {  
            // no roles specified, so nothing to check - allow access.  
            return true;  
        }
		
		Set<String> roles = CollectionUtils.asSet(rolesArray);
		for (String role : roles) {  
			if (subject.hasRole(role)) {  
            	return true;  
            }
		}
		
		return false;
	}
	
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Subject subject = getSubject(request, response);
        // If the subject isn't identified, redirect to login URL
        if (subject.getPrincipal() == null) {
        	// Convenience method for subclasses to use when a login redirect is required
        	saveRequestAndRedirectToLogin(request, response);
        } else {
            // If subject is known but not authorized, redirect to the unauthorized URL if there is one
            // If no unauthorized URL is specified, just return an unauthorized HTTP status code
            String unauthorizedUrl = getUnauthorizedUrl();
            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
	}

}
