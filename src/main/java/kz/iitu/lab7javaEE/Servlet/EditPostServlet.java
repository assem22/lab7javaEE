package kz.iitu.lab7javaEE.Servlet;


import kz.iitu.lab7javaEE.DAO.MyDao;
import kz.iitu.lab7javaEE.JavaBean.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EditPostServlet")
public class EditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");
		Post post = new Post();

		if(postId != null && !(postId.equals(""))){
			try {
				post = new MyDao().selectOne(Integer.parseInt(postId));
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			request.getSession().setAttribute("post", post);
			request.getSession().setAttribute("post_id", post.getId());
			response.sendRedirect("EditPost.jsp");
		}else{
			request.getSession().setAttribute("message", "Error !!!! Please select one Category!!!!!!");
			response.sendRedirect("PostList.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postId = (int) request.getSession().getAttribute("post_id");
		String theme= request.getParameter("theme");
		String body = request.getParameter("body");
		Post post = new Post();
		HttpSession adminSession = request.getSession(false);

		boolean status = false;
		PrintWriter out = response.getWriter();
		if(!theme.equals("") && !body.equals("") && postId != 0){
			post.setId(postId);
			post.setTheme(theme);
			post.setBody(body);
			try {
				status = new MyDao().updateEditedPost(post);
			} catch (Exception e) {
				adminSession.setAttribute("exception",e);
				e.printStackTrace();
			}
			if(status){
				out.println("<script>alert('You Have Succesfully Added new Movie !!!')</script>");
				adminSession.setAttribute("message","You Have Succesfully Added new Movie !!!" );
				response.sendRedirect("PostList.jsp");
			}else{
				out.println("<script>alert('Error !!! You Have Added duplicate Movie  !!!')</script>");
				adminSession.setAttribute("message","Error !!! You Have Added duplicate Movie !!!" );
				response.sendRedirect("PostList.jsp");
			}
		}else{
			out.println("<script>alert('Write Movie Details Again !!!')</script>");
			response.sendRedirect("PostList.jsp");
		}
	}

}
