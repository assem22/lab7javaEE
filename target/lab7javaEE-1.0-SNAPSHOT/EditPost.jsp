<%@ page import="kz.iitu.lab7javaEE.JavaBean.Post" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Product Details</title>
	<%-- Here I used declaration scriptlet to declare Movie object (also I can do it also in
	statement scriptlet), and here I used comment scriptlet --%>
	<%! Post post; %>
	<%
//		Movie post;
		post = (Post) request.getSession().getAttribute("post");
		if (post == null) {
			request.getSession().setAttribute("message",
					"Error!!!!!!!! Select Product First.");
			response.sendRedirect("PostList.jsp");
		}
		assert post != null;%>
</head>
<%@ include file = "bodyStart.jsp" %>
<a href="UserLogoutServlet">
	<button type="button">Logout</button>
</a>
<div align="center">
	<h2>Add New Product</h2>
</div>
<div>
	<form action="EditPostServlet" method="post">
		<table align="center">
<%--			<tr>--%>
<%--				<td>Post Id :</td>--%>
<%--				<td><input type="text" name="post_id" readonly="readonly"--%>
<%--						   value="<%=post.getId()%>" /></td>--%>
<%--			</tr>--%>
			<tr>
				<td>Post theme :</td>
				<td><input type="text" name="theme" required="true"
						   value="<%=post.getTheme()%>" /></td>
			</tr>
			<tr>
				<td>Post body :</td>
				<td><input type="text" name="body" required="true"
						   value="<%=post.getBody()%>" /></td>
			</tr>
			<tr>
				<td>Likes :</td>
				<td><%=post.getLikes()%></td>
			</tr>
			<tr>
				<td>Dislikes :</td>
				<td><%=post.getDislikes()%></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input value="Update Post"
													  type="submit" /></td>
			</tr>
		</table>
	</form>
</div>
<%@ include file = "footer.jsp" %>

