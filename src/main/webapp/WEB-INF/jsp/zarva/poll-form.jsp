<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form name="pollForm" action="save" method="post">
    <label for="firstname">First name</label>
    <input id="firstname" type="text" name="firstname" value="">
    <br>
    <label for="age">Age</label>
    <input id="age" type="number" name="age" value="">
    <br>
    <label for="startToSmokeAge">When did u start to smoke?</label>
    <input id="startToSmokeAge" type="number" name="startToSmokeAge" value="">
    <br>
    <label for="cigarettePerDay">How many cigarettes do u smoke per day.</label>
    <input id="cigarettePerDay" type="number" name="cigarettePerDay" value="">
    <br>
    <input type="submit">
</form>
</body>
</html>
