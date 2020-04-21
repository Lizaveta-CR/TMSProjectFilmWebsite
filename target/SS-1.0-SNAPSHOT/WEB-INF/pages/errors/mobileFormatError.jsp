<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 14.04.2020
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Mobile format error</title>
</head>
<body>
<p>
    <spring:message code="lbl.somethingiswrong"/>
    <spring:message code="Mobile.only.digits"/>
    <a href="/forgotPass"><spring:message code="lbl.exit"/></a>
</p>
</body>
</html>
