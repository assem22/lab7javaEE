<%@page import="java.util.List"%>
<%@ page import="kz.iitu.lab7javaEE.JavaBean.Post" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>List Categories</title>
    <% List<?> posts = (List<?>) request.getSession().getAttribute("posts");%>
    <%! Post post; %>
</head>
<%@ include file = "bodyStart.jsp" %>
<h2><a href="Login.jsp">Login</a></h2>
<div align="center" id="edit">
    <table border="1">
        <tr>
            <th width="150">Post theme</th>
            <th width="100">Post body</th>
            <th width="200">Like</th>
            <th width="80">Dislike</th>
            <th width="80">Comments</th>
        </tr>
        <%
            for (Object obj : posts) {
                post = (Post) obj;
        %>
        <tr>
            <td align="center"><%=post.getTheme()%></td>
            <td align="center"><%=post.getBody()%></td>
            <td align="center"><%=post.getLikes()%></td>
            <td align="center"><%=post.getDislikes()%></td>
            <td align="center"><a
                    href="PostDetailServlet?postId=<%=post.getId()%>">
                <button type="button">Comment</button>
            </a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<%@ include file = "footer.jsp" %>

