<%@ page import="kz.iitu.lab7javaEE.JavaBean.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.iitu.lab7javaEE.JavaBean.Comment" %><%--
  Created by IntelliJ IDEA.
  User: assemmukhamadi
  Date: 14.03.2021
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail Movie</title>
    <jsp:useBean id="post" class="kz.iitu.lab7javaEE.JavaBean.Post" scope="session"/>
    <% List<?> comments = (List<?>) request.getSession().getAttribute("comments");%>
    <jsp:useBean id="comment" class="kz.iitu.lab7javaEE.JavaBean.Comment" scope="session"/>

</head>
<%@ include file = "bodyStart.jsp" %>
<%--<h2><a href="Login.jsp">Login</a></h2>--%>
<div align="center">
    <h2>Add New Product</h2>
</div>
<div>
    <form action="AuthPostDetailServlet" method="post">
        <table align="center">
            <tr>
                <td>Post Theme :</td>
                <td><jsp:getProperty name="post" property="theme"/></td>
            </tr>
            <tr>
                <td>Post Body :</td>
                <td><jsp:getProperty name="post" property="body"/></td>
            </tr>
            <tr>
                <td>Likes :</td>
                <td><jsp:getProperty name="post" property="likes"/> <button type="button" onclick="<jsp:setProperty name="post" property="likes" value="${post.likes+1}"/>">like</button></td>
            </tr>
            <tr>
                <td>Dislikes :</td>
                <td><jsp:getProperty name="post" property="dislikes"/> <button type="button" onclick="<jsp:setProperty name="post" property="dislikes" value="${post.dislikes+1}"/>">dislike</button></td>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr>
                <th width="150">User</th>
                <th width="100">Comments</th>
            </tr>
            <%
                for (Object obj : comments) {
                    comment = (Comment) obj;
                    if (comment.getPost_id() == post.getId()){
            %>
            <tr>
                <td align="center"><%=comment.getUser_name()%></td>
                <td align="center"><%=comment.getComment()%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <div>
            <label>Add comment: </label>
            <input type="text" name="comment"/>
            <input value="Submit"
                   type="submit" />
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"/>
