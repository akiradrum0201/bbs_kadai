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

import bbs.beans.Posting;
import bbs.beans.User;
import bbs.service.PostingService;

@WebServlet(urlPatterns = { "/posting" })
public class PostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages) == true) {
			User user = (User) session.getAttribute("loginUser");
			Posting posting = new Posting();
			posting.setText(request.getParameter("text"));
			posting.setSubject(request.getParameter("subject"));
			posting.setCategory(request.getParameter("category"));
			posting.setUser_id(user.getId());

			new PostingService().register(posting);

			session.setAttribute("posting", posting);
			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("posting");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String text = request.getParameter("text");
		String subject = request.getParameter("subject");
		String category = request.getParameter("category");

		if(StringUtils.isEmpty(subject) == true) {
			messages.add("件名を入力してください");

		} else if(50 < subject.length()) {
			messages.add("件名は50文字以内で入力してください");
		}

		if(StringUtils.isEmpty(category) == true) {

			messages.add("カテゴリーを入力してください");
		} else if(10 < category.length()) {
			messages.add("カテゴリーは10文字以内で入力してください");

		}
		if(StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");

		} else if(1000 < text.length()) {
			messages.add("本文は1000文字以内で入力してください");

		}
		if(messages.size() == 0){
			return true;

		} else {
			return false;
		}
	}
}
