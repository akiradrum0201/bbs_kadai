package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.Branch;
import bbs.beans.Department;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.DepartmentService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/userManugement" })
public class UserManugementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<User> users= new UserService().getUser();
		List<Department> departments = new DepartmentService().getDepartment();
		List<Branch> branches = new BranchService().getBranch();

		request.setAttribute("users", users);
		request.setAttribute("department", departments);
		request.setAttribute("branch", branches);

		RequestDispatcher dispatcher = request.getRequestDispatcher("userManugement.jsp");
	    dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		if(request.getParameter("resurrection") !=null ) {
			int resurrection = Integer.parseInt(request.getParameter("resurrection"));
			new UserService().userResurrection(resurrection);

		}
		if(request.getParameter("is_stopped") !=null ) {
			int stopped = Integer.parseInt(request.getParameter("is_stopped"));
			new UserService().userStopped(stopped);
		}
		response.sendRedirect("userManugement");
	}
}