<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 21.04.2020
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Already admin</title>
</head>
<body>
<h1><spring:message code="lbl.somethingiswrong"></spring:message></h1>
${user.username} <spring:message code="lbl.isAdmin"></spring:message>
<a href="/admin/allUsers"><spring:message code="lbl.exit"></spring:message></a>
</body>
</html>
