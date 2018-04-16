package com.test.mystruts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action 接口，实现此接口的类称为子控制器
 * 
 * @author hezhao
 * 
 */
public interface Action {
	public String execute(HttpServletRequest request,
			HttpServletResponse response);
}
