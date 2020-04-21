<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 18.04.2020
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Admin-user orders</title>
</head>
<body>
<h1><spring:message code="lbl.adminUserOrdersTitle"/></h1>
<table border="1" style="width:100%">
    <tr>
        <th><spring:message code="lbl.orderDate"/></th>
        <th><spring:message code="lbl.loginUser"/></th>
        <th><spring:message code="lbl.filmPrice"/></th>
    </tr>
    <c:forEach items="${orders.list}" var="order">
    <tr>
        <td>${order.date}</td>
        <td>${order.user.username}</td>
        <td>${order.price}</td>
        </c:forEach>
</table>
<a href="/"><spring:message code="lbl.exit"/></a>
<a href="/admin/statistics"><spring:message code="lbl.statistics"/></a>
<c:if test="${orders.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${orders.navigationPages}" var="page">
            <c:if test="${page != -1 }">
                <a href="/admin/getAllOrdersFromStore?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
