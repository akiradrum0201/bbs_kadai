package bbs.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bbs.beans.Department;
import bbs.beans.User;
import bbs.beans.UserComment;
import bbs.beans.UserPosting;
import bbs.service.CommentService;
import bbs.service.DepartmentService;
import bbs.service.PostingService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Date date = new Date();
		String nowDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US).format(date);


		if(StringUtils.isEmpty(request.getParameter("start_date")) == true && (StringUtils.isEmpty(request.getParameter("end_date")) == true)) {
			List<UserPosting> postings = new PostingService().getPosting("2000-01-01", nowDate,"");
			request.setAttribute("postings", postings);

		} else {
			Timestamp getpastdate = null;
			Timestamp pastdate = new PostingService().getDate(getpastdate);
			String pastDate = new SimpleDateFormat("yyyy-MM-dd").format(pastdate);
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			String findCategory = request.getParameter("findCategory");

			if(StringUtils.isEmpty(start_date) == false && StringUtils.isEmpty(end_date) == false && StringUtils.isEmpty(findCategory) == false) {
				List<UserPosting> postings = new PostingService().getPosting(start_date, end_date, findCategory);
				request.setAttribute("postings", postings);
				request.setAttribute("start_date", start_date);
				request.setAttribute("end_date", end_date);
			}

			if((StringUtils.isEmpty(start_date) == false && StringUtils.isEmpty(end_date) == false && StringUtils.isEmpty(findCategory) == true)) {
				List<UserPosting> postings = new PostingService().getPosting(start_date, end_date , findCategory);
				request.setAttribute("postings", postings);
				request.setAttribute("start_date", start_date);
				request.setAttribute("end_date", end_date);

			} else {
				List<UserPosting> postings = new PostingService().getPosting(pastDate, nowDate , findCategory);
				request.setAttribute("postings", postings);
				request.setAttribute("start_date", start_date);
				request.setAttribute("end_date", end_date);

			}
		}

		User user = (User) request.getSession().getAttribute("loginUser");

		List<UserComment> comments = new CommentService().getComment();
		List<Department> departments = new DepartmentService().getDepartment();


		request.setAttribute("departments", departments);
		request.setAttribute("comments", comments);
		request.setAttribute("isShowMessageForm",  user != null);
		request.getRequestDispatcher("/top.jsp").forward(request,response);
	}
}

