package com.jt.web.utility;

import javax.servlet.http.HttpServletRequest;

public class Request {
	public static boolean isLocal(HttpServletRequest request)
    {
        boolean isLocal = false;
        if (request.getRequestURL().toString().contains("localhost")) {
            isLocal = true;
        }
        return isLocal;
    }
}
