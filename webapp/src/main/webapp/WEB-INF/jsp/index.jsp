<%-- Comments so as to know from where the variables come from --%>
<%--@elvariable id="user" type="ar.edu.itba.paw.models.User"--%>

<%--
    This was the one Sotuyo gave us. The second one is the recommended one.
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WebApp Index</title>
</head>
<body>

<h1><spring:message code="greeting" arguments="${user}"/></h1>
<h1><spring:message code="sessionId" arguments="${loggedUser.id}"/></h1>

</body>
</html>