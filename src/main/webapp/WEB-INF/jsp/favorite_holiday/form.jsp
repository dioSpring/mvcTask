<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title></title>
</head>
<body>

<form name="holidayForm" action="save" method="post">
    <label for="memorableHoliday">What is you memorable holiday in your life?</label>    
    <input id="memorableHoliday" name="memorableHoliday" value="">
    <br>
    <label for="unlikedHoliday">What is most unliked holiday for you?</label>
    <input id="unlikedHoliday" type="number" name="unlikedHoliday" value="">
    <br>
    <input type="submit">
</form>
</body>
</html>
