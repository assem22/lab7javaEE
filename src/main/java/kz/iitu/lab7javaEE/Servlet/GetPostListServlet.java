package kz.iitu.lab7javaEE.Servlet;


import kz.iitu.lab7javaEE.DAO.MyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/GetPostList")
public class GetPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyDao postDao = new MyDao();
		List<?> posts = null;
		try {
			posts = postDao.select();
		} catch (Exception e) {
			request.getSession().setAttribute("exception",e.getMessage());
		}
		request.getSession().setAttribute("posts",posts);
		response.sendRedirect("index.jsp");
	}

}
