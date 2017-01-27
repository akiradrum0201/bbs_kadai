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

import bbs.beans.Comment;
import bbs.beans.User;
import bbs.service.CommentService;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("top.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		List<String> message = new ArrayList<String>();

		if(isValid(request, message) ==true ) {
			User user = (User) session.getAttribute("loginUser");
			Comment comment = new Comment();
			comment.setText(request.getParameter("comment"));
			comment.setUser_id(user.getId());
			int posting_id = Integer.parseInt(request.getParameter("posting_id"));
			comment.setPosting_id(posting_id);

			new CommentService().register(comment);

			session.setAttribute("comments", comment);
			response.sendRedirect("./");
		} else {
			session.setAttribute("message", message);
			response.sendRedirect("./");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> message) {
		String comment = request.getParameter("comment");

		if(StringUtils.isEmpty(comment) == true) {
			message.add("コメントを入力してください");
		}
		if(500 < comment.length()) {
			message.add("コメントは500文字以下で入力してください");
		}
		if(message.size() == 0) {
			return true;

		} else {
			return false;
		}
	}


}
