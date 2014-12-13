<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 7.11.2014
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<h2 class="content-subhead">Listing</h2>
<sf:form class="pure-form pure-form-stacked" method="post" modelAttribute="listingHelper">
    <fieldset>
        <div class="pure-g">
            <div class="pure-u-1 pure-u-md-1-4">
                <label for="start-date">Start Date</label>
                <fmt:formatDate value="${time}" type="date" pattern="yyyy-MM-dd" var="tmpDate" />
                <sf:input path="startDate" id="start-date" type="date" value="${tmpDate}" />
            </div>

            <div class="pure-u-1 pure-u-md-1-4">
                <label for="end-date">End Date</label>
                <sf:input path="endDate" id="end-date" type="date" value="${tmpDate}" />
            </div>

            <div class="pure-u-1 pure-u-md-1-4">
                <label for="type">Type</label>
                <sf:select path="type" id="type">
                    <sf:option value="All" label="All" />
                    <sf:option value="transfer" label="Transfer" />
                    <sf:option value="withdraw" label="Withdraw" />
                    <sf:option value="deposit" label="Deposit" />
                </sf:select>
            </div>
            <div class="pure-u-1 pure-u-md-1-4">
                <label for="ll">.</label>
                <button id="ll" type="submit" class="pure-button pure-button-primary">Submit</button>
            </div>
        </div>
    </fieldset>
</sf:form>
<h3>${text}</h3>
<c:if test="${not empty transactions}">
    <table class="pure-table pure-table-horizontal" id="listTable">
        <thead>
        <tr>
            <th>#</th>
            <th>Creation Date</th>
            <th>Amount</th>
            <th>Account To</th>
            <th>Bank Code</th>
            <th>Detail</th>
        </tr>
        </thead>
        <tbody>
            <c:set var="count" value="1" scope="page" />
            <c:forEach var="tran" items="${transactions}">
                <tr class="pure-table-odd">
                    <td>${count}</td>
                    <td><fmt:formatDate value="${tran.creationDate}" pattern="dd-MM-yyyy"/></td>
                    <td>${tran.amount}</td>
                    <c:choose>
                        <c:when test="${tran.accountIdTo.accountNumber == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>${tran.accountIdTo.accountNumber}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${tran.bankCode.name}</td>
                    <td><a href="<s:url value="/transaction/${tran.txId}"/>">view</a></td>
                </tr>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
        </tbody>
    </table>
</c:if>

