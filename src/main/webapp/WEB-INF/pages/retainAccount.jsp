<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 29.04.2020
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Retain Account</title>
</head>
<body>
<form:form method="POST" modelAttribute="email">
    <td><spring:message code="lbl.email"/>:</td>
    <td><input type='text' name='email'></td>
    <input name="<spring:message code="lbl.submit"/>" type="submit" value="submit"/></td>
</form:form>
<a href="/login"><spring:message code="lbl.exit"/></a>
</body>
</html>
