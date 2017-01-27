package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.beans.User;


@WebFilter(urlPatterns = {"/userManugement", "/setting", "/signup"})
public class AuthorityFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		User user = (User) session.getAttribute("user");
		if(user.getDepartment_id() != 1) {
			String message = "権限がありません";
			session.setAttribute("message", message);
			HttpServletResponse res = ((HttpServletResponse)response);
			res.sendRedirect("./");

		} else {
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig config) throws ServletException {
	}
	public void destroy() {
	}

}

