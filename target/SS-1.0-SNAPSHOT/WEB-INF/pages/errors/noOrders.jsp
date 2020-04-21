<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 15.04.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <title>No orders</title>
</head>
<body>
<spring:message code="lbl.noOrders"/>
<a href="/getAllFilmsFromStore"><spring:message code="lbl.shop"/></a>
</body>
</html>
