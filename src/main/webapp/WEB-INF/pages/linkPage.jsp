<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 21.04.2020
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Order Confirmed</title>
</head>
<body>
<h1><spring:message code="lbl.orderConfirmedTitle"></spring:message></h1>
<c:forEach items="${nameLink}" var="entry">
<p> <spring:message code="lbl.filmName"></spring:message>: ${entry.key}, <spring:message
        code="lbl.link"></spring:message>: ${entry.value}<p>
    </c:forEach>
    <a href="/getAllFilmsFromStore"><spring:message code="lbl.exit"></spring:message>
</body>
</html>
