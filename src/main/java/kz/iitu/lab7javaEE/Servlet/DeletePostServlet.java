package kz.iitu.lab7javaEE.Servlet;


import kz.iitu.lab7javaEE.DAO.MyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
		if(postId != null && !(postId.equals(""))){
			boolean status = false;
			try {
				status = new MyDao().deletePost(Integer.parseInt(postId));
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			if(status){
				request.getSession().setAttribute("message", "Selected Movie has been deleted Successfully.");
				response.sendRedirect("PostList.jsp");
			}else{
				request.getSession().setAttribute("message", "Error !!!! Selected Movie has not been deleted !!!");
			}
		}else{
			request.getSession().setAttribute("message", "Error !!!! Please select one Category!!!!!!");
			response.sendRedirect("PostList.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
