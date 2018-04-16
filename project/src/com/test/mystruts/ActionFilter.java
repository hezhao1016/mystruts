package com.test.mystruts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.mystruts.manager.ActionManager;
import com.test.mystruts.manager.ActionMappingManager;
import com.test.mystruts.mapping.ActionMapping;
import com.test.mystruts.mapping.ActionResult;
import com.test.mystruts.mapping.ResultType;

/**
 * mystruts核心类，总控制器
 * 
 * @author hezhao
 * 
 */
public class ActionFilter implements Filter {

	private FilterConfig config;

	private ActionMappingManager mappingManager;

	@Override
	public void destroy() {
	}

	/**
	 * 控制器入口
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 将请求与响应转换成HttpServletRequest、HttpServletResponse
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		try {
			ActionMapping mapping = this.getActionMapping(request);
			// 获取action
			Action action = ActionManager.createAction(mapping.getClassName());
			// 获得action的execute()方法的执行结果
			String resultName = action.execute(request, response);
			// 获取actionResult对象
			ActionResult actionResult = mapping.getResult(resultName);
			// 页面跳转
			sendredirectForResultType(request, response, actionResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化，实例化ActionMappingManager
	 */
	@Override
	public void init(FilterConfig conf) throws ServletException {
		this.config = conf;
		String conStr = config.getInitParameter("config");
		// 可以包含多个配置文件
		String[] configFiles = null;
		if (conStr == null || conStr.isEmpty()) {
			configFiles = new String[] { "mystruts.xml" };
		} else {
			// 拆分配置文件名称字符串
			configFiles = conStr.split(",");
		}
		this.mappingManager = new ActionMappingManager(configFiles);
	}

	/**
	 * 获得ActionMapping
	 * 
	 * @param request
	 * @return ActionMapping
	 * @throws Exception
	 */
	private ActionMapping getActionMapping(HttpServletRequest request)
			throws Exception {
		// 获取请求的路径
		String uri = request.getRequestURI();
		// 获取上下文路径
		String contextPath = request.getContextPath();
		// 截取上下文路径以后的部分
		String actionPath = uri.substring(contextPath.length());
		// 获取Action名称
		String actionName = actionPath.substring(
				actionPath.lastIndexOf("/") + 1, actionPath.lastIndexOf("."))
				.trim();
		ActionMapping mapping = null;
		mapping = mappingManager.getActionMapping(actionName);
		return mapping;
	}

	/**
	 * 根据ResultType选择跳转方式
	 * 
	 * @param request
	 * @param response
	 * @param actionResult
	 * @throws IOException
	 * @throws ServletException
	 */
	private void sendredirectForResultType(HttpServletRequest request,
			HttpServletResponse response, ActionResult actionResult)
			throws IOException, ServletException {
		if (actionResult != null) {
			// 要跳转的url
			String viewName = actionResult.getValue();
			ResultType resultType = actionResult.getResultType();
			switch (resultType) {
			case Redirect:
				response.sendRedirect(request.getContextPath() + viewName);
				break;
			case Forward:
				request.getRequestDispatcher(viewName).forward(request,
						response);
				break;
			case Ajax:
				PrintWriter out = response.getWriter();
				out.println(viewName);
				out.close();
				break;
			case Chain:
				request.getRequestDispatcher(viewName).forward(request,
						response);
				break;
			case RedirectChain:
				response.sendRedirect(request.getContextPath() + viewName);
				break;
			case None:
				break;
			default:
				break;
			}
		}
	}

}
