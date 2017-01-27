package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.beans.User;
import bbs.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(login_id, password);
		User users = (User) request.getSession().getAttribute("loginUser");

		HttpSession session = request.getSession();

		session.setAttribute("user", user);
		session.setAttribute("users", users);
		if(user == null) {
			List<String> messages = new ArrayList<String>();
			messages.add("ログインに失敗しました。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login");
		}
		else if(user.getIs_stopped()==1) {
			List<String> messages = new ArrayList<String>();
			messages.add("アカウントが停止中です");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login");
		} else {
			session.setAttribute("loginUser", user);
			response.sendRedirect("./");
		}
	}
}









