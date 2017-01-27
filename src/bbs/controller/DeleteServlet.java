package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bbs.service.CommentService;
import bbs.service.PostingService;


@WebServlet(urlPatterns = { "/delete" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("top.jsp").forward(request, response);
		response.sendRedirect("./");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(!StringUtils.isEmpty(request.getParameter("deletePosting"))) {
			int deletePosting = Integer.parseInt(request.getParameter("deletePosting"));
			new PostingService().deletePosting(deletePosting);
		}

		if(!StringUtils.isEmpty(request.getParameter("deleteComment"))){
			int deleteComment = Integer.parseInt(request.getParameter("deleteComment"));
			new CommentService().deleteComment(deleteComment);
		}
		response.sendRedirect("./");



	}
}
