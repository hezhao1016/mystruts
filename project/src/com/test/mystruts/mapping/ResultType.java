package com.test.mystruts.mapping;

/**
 * Result返回结果类型
 * 
 * @author hezhao
 * 
 */
public enum ResultType {
	/**
	 * 重定向
	 */
	Redirect,
	/**
	 * 转发
	 */
	Forward,
	/**
	 * 异步请求
	 */
	Ajax,
	/**
	 * 数据流
	 */
	Stream,
	/**
	 * 跳转到向下一个控制器
	 */
	Chain,
	/**
	 * 重定向到下一个控制器
	 */
	RedirectChain,
	/**
	 * 无操作
	 */
	None
}
