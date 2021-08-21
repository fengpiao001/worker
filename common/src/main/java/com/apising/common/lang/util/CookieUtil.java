package com.apising.common.lang.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	public static Cookie newCookie(String name, String value, String domain) throws Exception {
		return newCookie(name, value, domain, null);
	}

	public static Cookie newCookie(String name, String value, String domain, Integer maxAge) throws Exception {
		return newCookie(name, value, domain, maxAge, null);
	}

	public static Cookie newCookie(String name, String value, String domain, Integer maxAge, String path)
			throws Exception {
		Cookie cookie = new Cookie(name, value);
		if (!StringUtil.isEmpty(domain)) {
			cookie.setDomain(domain);
		}
		if (null != maxAge) {
			cookie.setMaxAge(maxAge);
		}
		if (!StringUtil.isEmpty(path)) {
			cookie.setPath(path);
		}
		return cookie;
	}
	
	public static  String getStringFromCookie(HttpServletRequest request, String name,  String defaultValue) throws Exception {
	        Cookie[] cookies = request.getCookies();
	        if (CollectionUtil.isEmpty(cookies) || StringUtil.isEmpty(name)) {
	            return null;
	        }
	        for (Cookie cookie : cookies) {
	            if (!name.equals(cookie.getName())) {
	            	 continue;
	            } 
	            return cookie.getValue();
	        }
	        return null;
	    } 

}
