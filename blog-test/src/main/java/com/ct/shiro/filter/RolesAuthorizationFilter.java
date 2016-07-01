package com.ct.shiro.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

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
	
	protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1) throws IOException {
		return super.onAccessDenied(arg0, arg1);
	}

}
