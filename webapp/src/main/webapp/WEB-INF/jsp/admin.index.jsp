<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WebApp Index</title>
</head>
<body>

<h1>solo para admins</h1>
<h1><spring:message code="greeting" arguments="${user}"/></h1>
<h1><spring:message code="sessionId" arguments="${userId}"/></h1>

</body>
</html>