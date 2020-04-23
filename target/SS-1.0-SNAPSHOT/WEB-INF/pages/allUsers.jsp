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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <title>All users in system </title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        table#t01 {
            width: 100%;
            background-color: #f1f1c1;
        }
    </style>
</head>
<body>
<h2><spring:message code="lbl.allUsersTitle"/></h2>
<a href="/"><spring:message code="lbl.exit"/></a>
<table id="t01" style="width:100%">
    <tr>
        <th><spring:message code="lbl.loginUser"/>,<spring:message code="lbl.role"/></th>
        <th><spring:message code="lbl.add"/></th>
        <security:authorize access="hasRole('ROLE_ADMIN') and not hasRole('ROLE_USER')">
            <th><spring:message code="lbl.delete"/></th>
        </security:authorize>
    </tr>
    <c:forEach items="${users.iterator()}" var="user">
        <tr>
            <td>${user.username}</td>
            <td><a href="/admin/addAdmin/${user.username}"><spring:message code="lbl.add"/></a></td>
            <security:authorize access="hasRole('ROLE_ADMIN') and not hasRole('ROLE_USER')">
                <td><a href="/admin/deleteAdmin/${user.username}"><spring:message code="lbl.deleteAdminAuthority"/></a></td>
            </security:authorize>
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
