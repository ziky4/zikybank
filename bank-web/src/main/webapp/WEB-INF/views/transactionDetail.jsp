<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 14.11.2014
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content">
    <h2 class="content-subhead">Transaction detail</h2>
    <table class="pure-table">
        <tbody>
        <tr class="pure-table-odd">
            <td><b>Id</b></td>
            <td>${transaction.txId}</td>
        </tr>

        <tr>
            <td><b>Creation Date</b></td>
            <td><fmt:formatDate value="${transaction.creationDate}" pattern="dd-MM-yyyy"/></td>
        </tr>

        <tr class="pure-table-odd">
            <td><b>Amount</b></td>
            <td>${transaction.amount}</td>
        </tr>

        <tr>
            <td><b>Currency</b></td>
            <td>${transaction.currencyCode.name}</td>
        </tr>

        <tr class="pure-table-odd">
            <td><b>Bank</b></td>
            <td>${transaction.bankCode.name}</td>
        </tr>

        <tr>
            <td><b>Account from</b></td>
            <td>${transaction.accountIdFrom.accountNumber}</td>
        </tr>

        <tr class="pure-table-odd">
            <td><b>Account to</b></td>
            <c:choose>
                <c:when test="${transaction.accountIdTo.accountNumber == 0}">
                    <td></td>
                </c:when>
                <c:otherwise>
                    <td>${transaction.accountIdTo.accountNumber}</td>
                </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td><b>Type</b></td>
            <td>${transaction.typeId.description}</td>
        </tr>
        </tbody>
    </table>
</div>
