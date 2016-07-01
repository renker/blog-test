package com.ct.shiro;

import javax.annotation.Resource;
import javax.security.auth.login.AccountException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ct.person.entity.Person;
import com.ct.person.service.IPersonService;
import com.ct.utils.MD5Util;

public class AdminRealm extends AuthorizingRealm{

	@Resource
	private IPersonService personService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//simpleAuthorizationInfo.addRole("admin");
		return simpleAuthorizationInfo;
	}

	/**
	 * 登陆认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		String account = token.getUsername();
		String password = new String(token.getPassword());
		
		Person person = personService.login(account);
		if(person != null){
			person = personService.login(account, MD5Util.MD5(password));
			if(person != null){
				return new SimpleAuthenticationInfo(account,password,  token.getUsername());
			}else{
				throw new IncorrectCredentialsException("密码验证错误");
			}
		}else{
			throw new UnknownAccountException("账号验证错误");
		}
		
	}
	
}
