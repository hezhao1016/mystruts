package com.test.mystruts.mapping;

/**
 * Result封装类
 * 
 * @author hezhao
 * 
 */
public class ActionResult {
	private String name;
	private String value;
	private ResultType resultType;

	public ActionResult(String name, String value, ResultType resultType) {
		super();
		this.name = name;
		this.value = value;
		this.resultType = resultType;
	}

	public ActionResult() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		if (resultType == null || resultType.isEmpty()) {
			this.resultType = ResultType.Forward;// 默认跳转方式 转发
			return;
		}
		if ("redirect".equals(resultType.trim())) {
			this.resultType = ResultType.Redirect;
		} else if ("none".equals(resultType.trim())) {
			this.resultType = ResultType.None;
		} else if ("ajax".equals(resultType.trim())) {
			this.resultType = ResultType.Ajax;
		} else if ("stream".equals(resultType.trim())) {
			this.resultType = ResultType.Stream;
		} else if ("chain".equals(resultType.trim())) {
			this.resultType = ResultType.Chain;
		} else if ("redirectChain".equals(resultType.trim())) {
			this.resultType = ResultType.RedirectChain;
		} else {
			this.resultType = ResultType.Forward;
		}
	}

}
