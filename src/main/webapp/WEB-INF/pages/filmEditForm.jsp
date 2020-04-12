<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 09.04.2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <title>Edit Price</title>
</head>
<body>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <form:form action="/admin/edit" method="POST" modelAttribute="film">
        <%--        <spring:bind path="price">--%>
        <label><spring:message code="lbl.filmPrice"/>:</label>
        <form:input type="text" path="price"></form:input>
        <%--            <form:errors path="price" cssClass="error"></form:errors>--%>
        <%--        </spring:bind>--%>

        <button type="submit"><spring:message code="lbl.submit"/></button>
    </form:form>
</security:authorize>

</body>
</html>
