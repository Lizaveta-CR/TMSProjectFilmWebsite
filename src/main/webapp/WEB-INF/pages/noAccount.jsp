<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 29.04.2020
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <title>Deletion</title>
</head>
<body>
<h4><spring:message code="lbl.noAccountTitle"></spring:message></h4>
<a href="/retainAccount"><spring:message code="lbl.retainAccount"></spring:message></a>
<a href="/login"><spring:message code="lbl.exit"/></a>
</body>
</html>
