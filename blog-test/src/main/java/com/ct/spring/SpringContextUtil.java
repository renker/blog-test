package com.ct.spring;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class SpringContextUtil implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.applicationContext = ac;
	}
	
	public void getAllRequestMappings(){
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Controller.class);
		for (String key : beans.keySet()) {
			Object bean = beans.get(key);
			
			if(bean.getClass().isAnnotationPresent(RequestMapping.class)){
				RequestMapping crm = bean.getClass().getAnnotation(RequestMapping.class);
				String [] vrmVal = crm.value();
				
				Method [] methods = bean.getClass().getMethods();
				for (Method method : methods) {
					if(method.isAnnotationPresent(RequestMapping.class)){
						RequestMapping mrm = method.getAnnotation(RequestMapping.class);
						String [] mrmVal = mrm.value();
						if(mrmVal != null && mrmVal.length>0){
							String url = ((vrmVal != null && vrmVal.length>0) ?vrmVal[0]:"")+"///////"+mrmVal[0];
							
							// 替换掉“/”,"\"
							url = url.replaceAll("/+", ".");
							url = url.replaceAll("(\\{\\w+\\})", "*");
							
							String partten = url.replaceAll("\\.", "/");
							System.out.println(url+"\t"+partten);
						}
					}
				}
			}
		}
	}

}
