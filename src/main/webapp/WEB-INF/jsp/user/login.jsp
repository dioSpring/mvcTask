<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title></title>
</head>
<body>

<form name="userForm" action="save" method="post">
    <label for="name">Name</label>    
    <input id="name" name="name" value="">
    <br>
    <label for="dateofbirth">Date of birth</label>
    <input id="dateofbirth" name="dateofbirth" value="">
    <br>
    <input type="submit">
</form>
</body>
</html>
