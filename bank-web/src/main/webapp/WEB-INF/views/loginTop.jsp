<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 7.11.2014
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="isAuthenticated()">
    <a href="/static/j_spring_security_logout">Sign out</a>
</security:authorize>
<security:authorize access="!isAuthenticated()">
    <a href="/login">Sign in</a>
</security:authorize>

