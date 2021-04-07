<%@page import="java.util.List"%>
<%@ page import="java.io.*" %>
<%@ page import="kz.iitu.lab7javaEE.JavaBean.Post" %>
<%@ page import="kz.iitu.lab7javaEE.JavaBean.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List Categories</title>
	<%
		List<?> posts = (List<?>) request.getSession().getAttribute("posts");
		User user = (User) request.getSession().getAttribute("logged_user");
	%>
	<%! Post post; %>
</head>
<%@ include file = "bodyStart.jsp" %>
<a href="UserLogoutServlet">
	<button type="button">Logout</button>
</a>
<div align="center" id="edit">
	<h3 align="center">Posts</h3>
	<table border="1">
		<tr>
			<th width="150">Post theme</th>
			<th width="100">Post body</th>
			<th width="200">Like</th>
			<th width="80">Dislike</th>
			<th width="80">Comments</th>
			<th width="80">Edit</th>
			<th width="80">Delete</th>
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
					href="AuthPostDetailServlet?postId=<%=post.getId()%>">
				<button type="button">Comment</button>
			</a></td>
			<td align="center">
				<%
					if (post.getUser_id() == user.getId()){
				%>
				<a
					href="EditPostServlet?postId=<%=post.getId()%>">
				<button type="button">Edit</button>
			</a></td>
			<td align="center"><a
					href="DeletePostServlet?postId=<%=post.getId()%>">
				<button type="button">Delete</button>
			</a></td>
				<%
					}
				%>
		</tr>
		<%
			}
		%>
	</table>
</div>
<div align="center">
	<a
			href="AddPost.jsp">
		<button type="button">New Post</button>
	</a>
</div>
<%@ include file = "footer.jsp" %>

