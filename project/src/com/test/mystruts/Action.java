package com.test.mystruts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action �ӿڣ�ʵ�ִ˽ӿڵ����Ϊ�ӿ�����
 * 
 * @author hezhao
 * 
 */
public interface Action {
	public String execute(HttpServletRequest request,
			HttpServletResponse response);
}
