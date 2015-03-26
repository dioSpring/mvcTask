<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form action="save" method="post" commandName="user">
    <label for="firstNameId">First Name</label>
    <form:input path="firstname" id="firstNameId"/>
    <form:errors path="firstname" cssStyle="color: red"/>
    <br>
    <label for="lastNameId">Last Name</label>
    <form:input path="lastname" id="lastNameId"/>
    <form:errors path="lastname" cssStyle="color: red;"/>
    <br>
    <label for="dateofbirthId">Date of birth</label>
    <form:input path="dateofbirth" id="dateofbirthId"/>
    <form:errors path="dateofbirth" cssStyle="color: red;"/>
    <br>
    <input type="submit" value="Login">
</form:form>
</body>
</html>
