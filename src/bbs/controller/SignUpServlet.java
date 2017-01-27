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

import org.apache.commons.lang.StringUtils;

import bbs.beans.Branch;
import bbs.beans.Department;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.DepartmentService;
import bbs.service.UserService;

@WebServlet(urlPatterns ={ "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branches = new BranchService().getBranch();
		List<Department> departments = new DepartmentService().getDepartment();
		List<User> users= new UserService().getUser();

		System.out.println(users.get(0));

		request.setAttribute("branches", branches);
		request.setAttribute("departments", departments);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		List<Branch> branches = new BranchService().getBranch();
		List<Department> departments = new DepartmentService().getDepartment();


		User user = new User();
		user.setLogin_id(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		user.setBranch_id(branch_id);
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		user.setDepartment_id(department_id);

		request.setAttribute("users", user);
		request.setAttribute("branches", branches);
		request.setAttribute("departments", departments);


		if(isValid(request, messages) == true) {
			response.sendRedirect("userManugement");
			new UserService().register(user);

		} else {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String checkLoginId =  (request.getParameter("login_id"));
		User checkLogin_id = new UserService().checkLogin(checkLoginId);
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password_confirmation = request.getParameter("password_confirmation");
		String name = request.getParameter("name");
		String login_idLegex = "^[0-9a-zA-Z]{6,20}";
		String passwordLegex = "^[-_@+*;:#$%&a-zA-Z0-9 ]{6,255}";

		if(StringUtils.isEmpty(login_id) == true ) {
			messages.add("ログインIDを入力してください");

		} else if(!login_id.matches(login_idLegex) ) {
			messages.add("ログインIDは半角英数字6文字以上20文字以下で入力してください");

		} else if(checkLogin_id != null) {
			messages.add("ログインIDが重複しています");

		}
		if(!password.matches(passwordLegex)) {
			messages.add("パスワードは記号を含む半角英数6文字以上255文字以下で入力してください");
		}

		if(!password.equals(password_confirmation)) {
			messages.add("パスワードが間違っています、もう1度入力してください");

		}
		if(StringUtils.isEmpty(name) == true ) {
			messages.add("名前を入力してください");

		} else if(name.length()>10) {
			messages.add("名前は10文字以下で入力してください");

		}
		if(messages.size() == 0) {
			return true;

		} else {
			return false;
		}
	}
}

