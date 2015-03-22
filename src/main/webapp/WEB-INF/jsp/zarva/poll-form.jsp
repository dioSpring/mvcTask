<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form action="save" method="post" modelAttribute="userBO">
    <label for="firstNameInput">First name</label>
    <form:input path="firstName" id="firstNameInput"/>
    <form:errors path="firstName" cssStyle="color : red"/>
    <br>
    <label for="ageInput">Age: </label>
    <form:input path="age" id="ageInput" />
    <form:errors path="age" cssStyle="color : red" />
    <br/>
    <label for="startToSmokeAge">When did u start to smoke?</label>
    <form:input path="startToSmokeAge" id="startToSmokeAge"/>
    <form:errors path="startToSmokeAge" cssStyle="color : red"/>
    <br>
    <label for="cigarettePerDay">How many cigarettes do u smoke per day.</label>
    <form:input  path="cigarettePerDay" id="cigarettePerDay"/>
    <form:errors path="cigarettePerDay" cssStyle="color : red"/>
    <br>
    <input type="submit" value="Submit">
</form:form>
</body>
</html>
