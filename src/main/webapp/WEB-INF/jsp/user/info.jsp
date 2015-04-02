<%@ page import="org.diosoft.spring.mvcTask.model.User" %>
<%@ page import="org.diosoft.spring.mvcTask.model.Questionaire" %>
<%@ page import="org.diosoft.spring.mvcTask.model.AboutSpace" %>
<%--
  Created by IntelliJ IDEA.
  User: yar
  Date: 26.03.15
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%User user = (User) request.getAttribute("user");%>
<h2>Information for <%out.println(user.getFirstname());%></h2>
<h4>Id <%out.println(user.getId());%></h4>
<h4>First Name <%out.println(user.getFirstname());%></h4>
<h4>Last Name <%out.println(user.getLastname());%></h4>
<h4>Date of birth <%out.println(user.getDateofbirth());%></h4>
<a href="/user/logout">Logout</a>
<div>Please select questionnaire</div>
<div><a href="/space/about-space-form">space</a><div>
<div><a href="/favorite_holiday/form">holliday</a><div>
</body>
</html>
