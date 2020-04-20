<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 18.04.2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" style="width:100%">
    <tr>
        <th><spring:message code="lbl.loginUser"/></th>
        <th><spring:message code="lbl.filmPrice"/></th>
    </tr>
    <c:forEach items="${statistics}" var="order">
        <tr>
            <td>${order.key.username}</td>
            <td>${order.value}</td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/getAllOrdersFromStore"><spring:message code="lbl.exit"/></a>
</body>
</html>
