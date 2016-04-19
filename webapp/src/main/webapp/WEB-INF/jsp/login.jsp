<%--
  Created by IntelliJ IDEA.
  User: matias
  Date: 18/04/16
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/login" method="post" enctype="application/x-www-form-urlencoded">
    <div>
        <label for="username">Username</label>
        <input id="username" name="username" type="text"/>
    </div>
    <div>
        <label for="password">Password</label>
        <input id="password" name="password" type="password"/>
    </div>
    <div>
        <input type="submit" value="Login"/>
    </div>
</form>
</body>
</html>
