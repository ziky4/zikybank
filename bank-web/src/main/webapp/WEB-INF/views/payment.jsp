<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 7.11.2014
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2 class="content-subhead">New Payment</h2>
<sf:form class="pure-form pure-form-aligned" method="post" modelAttribute="paymentHelper">
    <fieldset>
        <div class="pure-control-group">
            <label for="date">Date</label>
            <fmt:formatDate value="${time}" type="date" pattern="yyyy-MM-dd" var="tmpDate" />
            <input path="creationDate" id="date" type="date" min="${tmpDate}" value="${tmpDate}" >
        </div>
        <div class="pure-control-group">
            <label for="accountFrom">Acccount from</label>
            <input type="text" id="accountFrom" value="${accountNumber}" readonly>
        </div>
        <div class="pure-control-group">
            <label for="accountTo">Acccount to</label>
            <sf:input path="accountTo" id="accountTo" type="number" min="0" step="1" placeholder="Reciever account" />
        </div>
        <div class="pure-control-group">
            <label for="bankCode">Bank code</label>
            <sf:select path="bankCode" id="bankCode">
                <sf:options items="${banks}" itemLabel="name" itemValue="bankCode" />
            </sf:select>
        </div>
        <div class="pure-control-group">
            <label for="amount">Amount</label>
            <sf:input path="amount" id="amount" type="number" min="0" step="1" placeholder="${currency}" />
        </div>
        <div class="pure-controls">
            <button type="submit" class="pure-button pure-button-primary">Submit</button>
        </div>
    </fieldset>
</sf:form>
