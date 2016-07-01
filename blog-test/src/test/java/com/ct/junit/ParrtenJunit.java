package com.ct.junit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ParrtenJunit {
	@Test
	public void Pattern(){
		Pattern p = Pattern.compile("/**");
		
		String res = "/admin/person/index";
		
		System.out.println(res.replaceAll("(.{3}).+(.{3,}@.+)", "$2...$2"));
		
		Matcher m = p.matcher(res);
		System.out.println(m.group(0));
	}
}
