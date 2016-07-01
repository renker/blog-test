package com.ct.junit;

import java.util.UUID;

import org.junit.Test;

import com.ct.utils.MD5Util;

public class MD5Junit {
	@Test
	public void md5(){
		String a = MD5Util.MD5("admin");
		System.out.println(a);
	}
	
	@Test
	public void uuid(){
		String res = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		System.out.println(res);
	}
}
