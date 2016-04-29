<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>login</title>
</head>
<body>

<%--<c:url value="/login" var="loginUrl" />
<form modelAttribute="loginForm" action="${loginUrl}" method="post" enctype="application/x-www-form-urlencoded">
    <div>
        <label for="username">Username</label>
        <input id="username" type="text"/>
    </div>
    <div>
        <label for="password">Password</label>
        <input id="password" type="password"/>
    </div>
    <div>
        <label input name="j_rememberme" type="checkbox">
            <spring:message code="rember_me" />
        </label>
        <div>
            <input type="submit" value="Login"/>
        </div>
    </div>
</form>
</body>
</html>--%>

<%-- Copied from WebAuthConfig -> configure(http) -> loginPage --%>
<c:url value="/login" var="loginProcessingUrl"/>
<form modelAttribute="loginForm" action="${loginProcessingUrl}" method="post" enctype="application/x-www-form-urlencoded">
    <fieldset>
        <legend>Please Login</legend>
        <!-- use param.error assuming FormLoginConfigurer#failureUrl contains the query parameter error -->
        <c:if test="${param.error != null}">
            <div>
                Failed to login.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                    Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                </c:if>
            </div>
        </c:if>
        <!-- the configured LogoutConfigurer#logoutSuccessUrl is /login?logout and contains the query param logout -->
        <c:if test="${param.logout != null}">
            <div>
                You have been logged out.
            </div>
        </c:if>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <!-- if using RememberMeConfigurer make sure remember-me matches RememberMeConfigurer#rememberMeParameter -->
        <p>
            <label for="remember-me">Remember Me?</label>
            <input type="checkbox" id="remember-me" name="rememberme"/>
        </p>
        <div>
            <button type="submit" class="btn">Log in</button>
        </div>
    </fieldset>
</form>
<%--<form:form modelAttribute="loginForm" action="/login" method="post" enctype="application/x-www-form-urlencoded">
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
</form:form>--%>
</body>
</html>
