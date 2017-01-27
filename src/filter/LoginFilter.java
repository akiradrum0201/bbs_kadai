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


@WebFilter(urlPatterns = {"/comment", "/delete", "/posting", "/setting", "/userManugement", "/index.jsp"})
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		User user = (User) session.getAttribute("user");
		if(user == null) {
			String message = "ログインしてください";
			request.setAttribute("message", message);
			HttpServletResponse res = ((HttpServletResponse)response);
			res.sendRedirect("login");

		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

}
