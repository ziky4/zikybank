<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 7.11.2014
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:url var="authUrl" value="/static/j_spring_security_check" />
<security:authorize access="!isAuthenticated()">
    <form class="pure-form pure-form-stacked" method="post" action="${authUrl}">
        <fieldset>
            <legend>Sign in</legend>

            <label for="account">Account</label>
            <input name="j_username" id="account" type="text" placeholder="Account number">

            <label for="password">Password</label>
            <input name="j_password" id="password" type="password" placeholder="Password">

            <button name="commit" type="submit" class="pure-button pure-button-primary">Sign in</button>
            <label style="color: red">${error}</label>
        </fieldset>
    </form>
</security:authorize>
<security:authorize access="isAuthenticated()">
    You are already logged
</security:authorize>


