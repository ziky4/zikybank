<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 14.10.2014
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="isAuthenticated()">
    <ul>
        <form>
            <li><a href="/home">Home</a></li>
            <li><a href="/payment">Payment</a></li>
            <li><a href="/listing">Listing</a></li>
            <li><a href="/info">Info</a></li>
            <li><a href="#">Other</a></li>
        </form>
    </ul>
    <hr>
</security:authorize>
<security:authorize access="!isAuthenticated()">

</security:authorize>
