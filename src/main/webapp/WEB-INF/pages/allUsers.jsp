<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 20.04.2020
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>All users in system </title>
</head>
<body>
<h2><spring:message code="lbl.allUsersTitle"/></h2>
<table border="1" style="width:100%">
    <c:forEach items="${users.iterator()}" var="user">
        <tr>
            <th><spring:message code="lbl.loginUser"/>,<spring:message code="lbl.role"/></th>
        </tr>
        <tr>
            <td>${user.username}</td>
            &nbsp
        </tr>
        <c:forEach items="${user.userRole}" var="role">
            <tr>
                <td>${role.role}</td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
<a href="/"><spring:message code="lbl.exit"/></a>
</body>
</html>
