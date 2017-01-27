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


@WebServlet(urlPatterns = { "/setting" })
	public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int check_Id = Integer.parseInt(request.getParameter("id"));

		User checkId = new UserService().checkId(check_Id);
		User settingUser = new UserService().getSettingUser(check_Id);
		List<Branch> branches = new BranchService().getBranch();
		List<Department> departments = new DepartmentService().getDepartment();
		HttpSession session = request.getSession();
		if(checkId == null) {

			String message = "不正なアクセスです";
			session.setAttribute("errorMessages", new String(message));
			response.sendRedirect("userManugement");
			return;
		}

		request.setAttribute("branch", branches);
		request.setAttribute("department", departments);
		request.setAttribute("user", settingUser);
	    request.getRequestDispatcher("setting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Branch> branches = new BranchService().getBranch();
		List<Department> departments = new DepartmentService().getDepartment();

		User editUser = getEditUser(request);
		List<String> messages = new ArrayList<String>();
		if(isValid(request, messages) == true) {

			request.setAttribute("editUser", editUser);
			new UserService().userSetting(editUser);
			response.sendRedirect("userManugement");

		} else {
			request.setAttribute("user", editUser);
			request.setAttribute("branch", branches);
			request.setAttribute("department", departments);
			request.setAttribute("errorMessage", messages);
			request.getRequestDispatcher("setting.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String checkLoginId =  (request.getParameter("login_id"));
		User checkLogin_id = new UserService().checkLogin(checkLoginId);
		int id = Integer.parseInt(request.getParameter("id"));
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password_confirmation = request.getParameter("password_confirmation");
		String login_idLegex = "^[0-9a-zA-Z]{6,20}";
		String passwordLegex = "^[-_@+*;:#$%&a-zA-Z0-9 ]{6,255}";
		String name = request.getParameter("name");

		if(StringUtils.isEmpty(login_id) == true ) {
			messages.add("ログインIDを入力してください");
		} else if(!login_id.matches(login_idLegex) ) {
			messages.add("ログインIDは半角英数字6文字以上20文字以下で入力してください");
		} else if(checkLogin_id != null && id != checkLogin_id.getId()) {
			System.out.println(id);
			System.out.println(checkLogin_id.getId());
			messages.add("ログインIDが重複しています");
		}

		if(StringUtils.isEmpty(password) == false) {

			if(!password.matches(passwordLegex)) {
				messages.add("パスワードは記号を含む半角英数6文字以上255文字以下で入力してください");
			}

			if(!password.equals(password_confirmation)) {
				messages.add("パスワードが間違っています、もう1度入力してください");
			}
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

	private User getEditUser(HttpServletRequest request)throws IOException, ServletException {
		User editUser = new User();
		editUser.setLogin_id(request.getParameter("login_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		editUser.setId(id);
		editUser.setPassword(request.getParameter("password"));
		editUser.setName(request.getParameter("name"));
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));
		editUser.setBranch_id(branch_id);
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		editUser.setDepartment_id(department_id);

		return editUser;
	}



}
