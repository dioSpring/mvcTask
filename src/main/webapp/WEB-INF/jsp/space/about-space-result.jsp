<%@ page import="org.diosoft.spring.mvcTask.model.SpaceQuestionaire" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%SpaceQuestionaire spaceQuestionaire = (SpaceQuestionaire) request.getAttribute("spaceQuestionaire");%>
<h1><%out.println(spaceQuestionaire.getType().getDescription());%></h1>
<h4>About Space <%out.println(spaceQuestionaire.getAboutSpace());%></h4>
<h4>Earth Old <%out.println(spaceQuestionaire.getEarthOld());%></h4>
</body>
</html>
