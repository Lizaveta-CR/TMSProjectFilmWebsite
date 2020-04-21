<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page session="true" %>

<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
<form action="/admin/getFilmsFromSite" method="GET">
    <label for="filmNum"><spring:message code="lbl.numberOfFilms"/>(1-5):</label>
    <input type="number" id="filmNum" name="filmNum" min="1" max="5">
    <input name="submit" type="submit" value="<spring:message code="lbl.parsePage"/>"/>
</form>
<a href="/"><spring:message code="lbl.exit"/></a>
<a href="/admin/getAllOrdersFromStore"> <spring:message code="lbl.adminUserOrdersTitle"/></a>
<a href="/admin/allUsers"> <spring:message code="lbl.addAdmin"/></a>
</body>
</html>