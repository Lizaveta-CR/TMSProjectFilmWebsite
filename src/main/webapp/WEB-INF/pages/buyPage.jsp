<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 12.04.2020
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Order Details</title>
</head>
<body>
<h2><spring:message code="lbl.buyTitle"/></h2>
<label><spring:message code="lbl.loginUser"/>: ${user.username}</label>
<c:forEach items="${film.iterator()}" var="film">
    <div class="product-preview-container">
        <td><spring:message code="lbl.filmName"/>: ${film.name}</td>
        <td><spring:message code="lbl.filmPrice"/>: ${film.price}</td>
    </div>
</c:forEach>
<spring:message code="lbl.priceToPay"/>: ${price}
<a href="/addFilmToOrder"><spring:message code="lbl.add"/></a>
<a href="/confirmOrder/${user.username}"><spring:message code="lbl.buy"/></a>
</body>
</html>
