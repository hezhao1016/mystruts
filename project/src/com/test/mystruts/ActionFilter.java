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
 * mystruts�����࣬�ܿ�����
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
	 * ���������
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// ����������Ӧת����HttpServletRequest��HttpServletResponse
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		try {
			ActionMapping mapping = this.getActionMapping(request);
			// ��ȡaction
			Action action = ActionManager.createAction(mapping.getClassName());
			// ���action��execute()������ִ�н��
			String resultName = action.execute(request, response);
			// ��ȡactionResult����
			ActionResult actionResult = mapping.getResult(resultName);
			// ҳ����ת
			sendredirectForResultType(request, response, actionResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ����ʵ����ActionMappingManager
	 */
	@Override
	public void init(FilterConfig conf) throws ServletException {
		this.config = conf;
		String conStr = config.getInitParameter("config");
		// ���԰�����������ļ�
		String[] configFiles = null;
		if (conStr == null || conStr.isEmpty()) {
			configFiles = new String[] { "mystruts.xml" };
		} else {
			// ��������ļ������ַ���
			configFiles = conStr.split(",");
		}
		this.mappingManager = new ActionMappingManager(configFiles);
	}

	/**
	 * ���ActionMapping
	 * 
	 * @param request
	 * @return ActionMapping
	 * @throws Exception
	 */
	private ActionMapping getActionMapping(HttpServletRequest request)
			throws Exception {
		// ��ȡ�����·��
		String uri = request.getRequestURI();
		// ��ȡ������·��
		String contextPath = request.getContextPath();
		// ��ȡ������·���Ժ�Ĳ���
		String actionPath = uri.substring(contextPath.length());
		// ��ȡAction����
		String actionName = actionPath.substring(
				actionPath.lastIndexOf("/") + 1, actionPath.lastIndexOf("."))
				.trim();
		ActionMapping mapping = null;
		mapping = mappingManager.getActionMapping(actionName);
		return mapping;
	}

	/**
	 * ����ResultTypeѡ����ת��ʽ
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
			// Ҫ��ת��url
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
