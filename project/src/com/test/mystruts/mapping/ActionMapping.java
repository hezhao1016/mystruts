package com.test.mystruts.mapping;

import java.util.HashMap;
import java.util.Map;

import com.test.mystruts.exception.MyStrutsException;

/**
 * Actionӳ���ļ���װ��
 * 
 * @author hezhao
 * 
 */
public class ActionMapping {
	private String name;
	private String className;
	private Map<String, ActionResult> actionResults = new HashMap<String, ActionResult>();

	public ActionMapping(String name, String className) {
		super();
		this.name = name;
		this.className = className;
	}

	public ActionMapping() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void addResult(String name, ActionResult actionResult) {
		this.actionResults.put(name, actionResult);
	}

	public ActionResult getResult(String name) throws Exception {
		ActionResult actionResult = this.actionResults.get(name);
		if (actionResult == null) {
			throw new MyStrutsException("δ�ҵ�nameΪ:[" + name
					+ "]��Result�����У�ActionName:" + this.getName()
					+ ",ResultName:" + name
					+ "������ԭ��:Action���ؽ����Result��һ�»�Result��name��������");
		}
		return actionResult;
	}

}
