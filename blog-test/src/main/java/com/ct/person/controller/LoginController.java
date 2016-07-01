package com.ct.person.controller;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.ct.person.service.IPersonService;

@Controller
@RequestMapping("admin")
public class LoginController {
	@Resource
	private IPersonService personService;
	
	@RequestMapping("login")
	public String login(Model model,String errorMsg){
		model.addAttribute("errorMsg", errorMsg);
		return "admin/login";
	}
	
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @param model
	 * @return
	 * @author chen yin tao
	 * @date 2016-4-17 下午5:08:28
	 */
	@RequestMapping(value="doLogin",method=RequestMethod.POST)
	public String doLogin(@RequestParam("username")String username,@RequestParam("password")String password,Boolean rememberMe,Model model){
		String errorMsg = null;
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			subject.login(token);
		} catch (AuthenticationException e) {
			if(e instanceof UnknownAccountException){
				errorMsg = "账号不存在";
			}else if(e instanceof IncorrectCredentialsException){
				errorMsg = "密码错误";
			}
			e.printStackTrace();
			model.addAttribute("errorMsg", errorMsg);
			return "redirect:/admin/login";
		}
		model.addAttribute("errorMsg", errorMsg);
		return "redirect:/admin/index";
	}
	
	/**
	 * 登出
	 * @return
	 * @author chen yin tao
	 * @date 2016-4-17 下午5:08:19
	 */
	@RequestMapping("loginOut")
	public String loginOut(){
		SecurityUtils.getSubject().logout();
		return "redirect:/admin/login";
	}
	
	@RequestMapping("index")
	public String index(){
		return "admin/index";
	}
	
	/**
	 * 非法进入提醒页面
	 * @return
	 */
	@RequestMapping("unauthorized")
	public String unauthorized(){
		return "admin/unauthorized";
	}
}
