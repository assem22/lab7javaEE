package kz.iitu.lab7javaEE.Servlet;


import javafx.geometry.Pos;
import kz.iitu.lab7javaEE.DAO.MyDao;
import kz.iitu.lab7javaEE.JavaBean.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PostDetailServlet")
public class PostDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("postId");
        List<?> comments = null;
        Post post = new Post();

        if(postId != null && !(postId.equals(""))){
            try {
                post = new MyDao().selectOne(Integer.parseInt(postId));
                comments = new MyDao().selectComments(post.getId());
            } catch (Exception e) {
                request.getSession().setAttribute("exception",e.getMessage());
            }
            request.getSession().setAttribute("post", post);
            request.getSession().setAttribute("comments",comments);
            response.sendRedirect("PostDetail.jsp");
        }else{
            request.getSession().setAttribute("message", "Error !!!! Please select one Category!!!!!!");
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
