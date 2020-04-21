<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 14.04.2020
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User Orders</title>
</head>
<body>
<c:forEach items="${orders.iterator()}" var="order">
    <div class="product-preview-container">
        <tr>
            <th><spring:message code="lbl.priceToPay"/></th>
            <td>${order.price}</td>
            &nbsp
            <c:if test="${empty order.films}">
            <td><spring:message code="lbl.filmnotavailable"/></td>
            </c:if>
            <c:forEach items="${order.films}" var="film">
        <tr>
            <td>${film.name}</td>
            <td>${film.price}</td>
            &nbsp
        </tr>
        </c:forEach>
        </tr>
    </div>
</c:forEach>
<a href="/"><spring:message code="lbl.exit"/></a>
</body>
</html>
