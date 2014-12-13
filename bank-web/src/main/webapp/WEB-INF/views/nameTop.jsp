<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 10.11.2014
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="isAuthenticated()">
 Logged number <security:authentication property="principal.username" />
</security:authorize>
<security:authorize access="!isAuthenticated()">
 Welcome
</security:authorize>


