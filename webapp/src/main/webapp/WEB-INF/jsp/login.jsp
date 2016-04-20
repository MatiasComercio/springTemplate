<%--
  Created by IntelliJ IDEA.
  User: matias
  Date: 18/04/16
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form:form modelAttribute="loginForm" action="/login" method="post" enctype="application/x-www-form-urlencoded">
    <div>
        <form:label path="username">Username</form:label>
        <form:input path="username" type="text"/>
        <form:errors path="username" cssStyle="color: red;" element="div"/>
    </div>
    <div>
        <form:label path="password">Password</form:label>
        <form:input path="password" type="password"/>
        <form:errors path="password" cssStyle="color: red;" element="div"/>
    </div>
    <div>
        <input type="submit" value="Login"/>
    </div>
</form:form>
</body>
</html>
