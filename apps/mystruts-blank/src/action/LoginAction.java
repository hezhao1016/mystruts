package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.mystruts.Action;

/**
 * µÇÂ¼
 * 
 * @author user
 * 
 */
public class LoginAction implements Action {

	/**
	 * Ö÷Âß¼­
	 */
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		if ("admin".equals(name) && "123".equals(pwd)) {
			request.getSession().setAttribute("name", name);
			return "success";
		}
		return "error";
	}

}
