<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form action="save" method="post" commandName="aboutSpace">
  <label for="aboutSpaceId">About Space</label>
  <form:input path="aboutSpace" id="aboutSpaceId"/>
  <form:errors path="aboutSpace" cssStyle="color: red"/>
  <br>
  <label for="earthOldId">Earth Old</label>
  <form:input path="earthOld" id="earthOldId"/>
  <form:errors path="earthOld" cssStyle="color: red;"/>
  <br>
  <input type="submit" value="Ok">
</form:form>
</body>
</html>
