<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 26.04.2020
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Email format error</title>
</head>
<body>
<p>
    <spring:message code="lbl.somethingiswrong"/>
    <spring:message code="Email.not.valid"/>
    <c:if test="${not empty errorMessage}">
<div class="msg">${errorMessage}</div>
</c:if>
<a href="/forgotPass"><spring:message code="lbl.exit"/></a>
</p>
</body>
</html>
