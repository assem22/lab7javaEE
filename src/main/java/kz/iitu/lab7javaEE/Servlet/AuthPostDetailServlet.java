package kz.iitu.lab7javaEE.Servlet;

import kz.iitu.lab7javaEE.DAO.MyDao;
import kz.iitu.lab7javaEE.JavaBean.Comment;
import kz.iitu.lab7javaEE.JavaBean.Post;
import kz.iitu.lab7javaEE.JavaBean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/AuthPostDetailServlet")
public class AuthPostDetailServlet extends HttpServlet {
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
            response.sendRedirect("AuthPostDetail.jsp");
        }else{
            request.getSession().setAttribute("message", "Error !!!! Please select one Category!!!!!!");
            response.sendRedirect("index.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);

        Comment newComment = new Comment();


        boolean status = false;
        MyDao myDao = new MyDao();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Post post = (Post) request.getSession().getAttribute("post");
        String comment = request.getParameter("comment");
        HttpSession userSession = request.getSession(false);
        User user = (User) request.getSession().getAttribute("logged_user");
        myDao.updatePost(post);

        if(!comment.equals("")){
            newComment.setPost_id(post.getId());
            newComment.setComment(comment);
            newComment.setUser_name(user.getName());
            try {
                status = myDao.insert(newComment);
            } catch (Exception e) {
                userSession.setAttribute("exception",e);
                e.printStackTrace();
            }
            if(status){
                out.println("<script>alert('You Have Succesfully Added new Movie !!!')</script>");
                userSession.setAttribute("message","You Have Succesfully Added new Movie !!!" );
                response.sendRedirect("AuthPostDetail.jsp");
            }else{
                out.println("<script>alert('Error !!! You Have Added duplicate Movie  !!!')</script>");
                userSession.setAttribute("message","Error !!! You Have Added duplicate Movie !!!" );
                response.sendRedirect("admin/AdminHome.jsp");
            }
        }else{
            out.println("<script>alert('Write Movie Details Again !!!')</script>");
            response.sendRedirect("AdminLogin.jsp");
        }
    }
}
