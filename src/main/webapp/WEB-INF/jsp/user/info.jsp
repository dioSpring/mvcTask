<%@ page import="org.diosoft.spring.mvcTask.model.User" %>
<%@ page import="org.diosoft.spring.mvcTask.model.Questionaire" %>
<%@ page import="org.diosoft.spring.mvcTask.model.SpaceQuestionaire" %>
<%@ page import="org.diosoft.spring.mvcTask.model.QuestionaireType" %>
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
<h2>Test Passed <%out.println(user.getQuestionaires().size());%></h2>
<h4>Passed</h4>
        <%for(Questionaire questionaire: user.getQuestionaires()){%>
        <a href="<%out.println(questionaire.getType().getBaseUrl());%>/result?id=<%out.println(questionaire.getId());%>"><%out.println(questionaire.getType().getDescription());%></a>
</br>
<%}%>
<h4>Available</h4>
<%for(QuestionaireType type: QuestionaireType.values()){%>
<a href="<%out.println(type.getBaseUrl());%>"><%out.println(type.getDescription());%></a>
<%}%>
<br>
<a href="/user/logout">Logout</a>
</body>
</html>
