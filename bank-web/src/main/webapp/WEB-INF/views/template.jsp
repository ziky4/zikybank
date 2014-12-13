<%--
  Created by IntelliJ IDEA.
  User: Ziky
  Date: 8.10.2014
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <meta http-equiv="CONTENT-TYPE">
        <meta content="text/html">
        <title>Ziky Bank</title>
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/grids-responsive-min.css">
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300&amp;subset=latin,latin-ext">
        <link href="<s:url value="/resources" />/css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
    <div class="topmenu">
        <div class="toptext">
            <a href="#"><img src="<s:url value="/resources" />/icon/cz.png" /></a>
            <a href="#"><img src="<s:url value="/resources" />/icon/en.png" /></a>
            <span class="mid">
                <t:insertAttribute name="name" />
            </span>
            <span class="left">
                <t:insertAttribute name="login" />
            </span>
        </div>
    </div>

    <header class="page-header">
        <div class="container">
            <h1 class="page-title">Ziky bank</h1>
            <h2 class="page-desc">Development of information systems</h2>
        </div>
    </header>

    <div class="container pure-menu pure-menu-open pure-menu-horizontal" id="menu">
        <t:insertAttribute name="top" />
    </div>

    <div class="content">
        <t:insertAttribute name="content" />
    </div>

    <footer class="footer">
        <div class="container">
            This project was created as semestral project for subject Development of information systems
        </div>
    </footer>
    </body>
</html>
