package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.mystruts.Action;

/**
 * ע��
 * 
 * @author user
 * 
 */
public class RegisterAction implements Action {

	/**
	 * ���߼�
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String repwd = request.getParameter("repwd");
		if (!"admin".equals(name) && pwd.trim().equals(repwd.trim())) {
			return "success";
		}
		return "error";
	}

}
