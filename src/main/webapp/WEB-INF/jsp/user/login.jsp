<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form commandName="user" action="login" method="post">
    <label for="usernameId">Username</label>
    <form:input path="username" id="usernameId"/>
    <form:errors path="username" cssStyle="color: red"/>
    <br>
    <label for="passwordId">Password</label>
    <form:password path="password" id="passwordId"/>
    <form:errors path="password" cssStyle="color: red;"/>
    <br>
    <input type="submit" value="Login">
</form:form>
</body>
</html>
