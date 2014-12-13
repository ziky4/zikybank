<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 13.11.2014
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content">
    <h2 class="content-subhead">Account info</h2>
    <table class="pure-table">
        <tbody>
        <tr class="pure-table-odd">
            <td><b>Account Number</b></td>
            <td>${account.accountNumber}</td>
        </tr>

        <tr>
            <td><b>Creation Date</b></td>
            <td><fmt:formatDate value="${account.creationTime}" pattern="dd-MM-yyyy"/></td>
        </tr>

        <tr class="pure-table-odd">
            <td><b>Balance</b></td>
            <td>${account.balance}</td>
        </tr>

        <tr>
            <td><b>Overdraft</b></td>
            <td>${account.overDraft}</td>
        </tr>

        <tr class="pure-table-odd">
            <td><b>Daily Limit</b></td>
            <td>${account.dailyLimit}</td>
        </tr>

        <tr>
            <td><b>Monthly Limit</b></td>
            <td>${account.monthlyLimit}</td>
        </tr>

        <tr class="pure-table-odd">
            <td><b>Type</b></td>
            <td>${account.typeId.name}</td>
        </tr>

        <tr>
            <td><b>Currency</b></td>
            <td>${account.currencyCode.name}</td>
        </tr>
        </tbody>
    </table>
</div>
