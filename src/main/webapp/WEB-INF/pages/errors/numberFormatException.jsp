<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 07.04.2020
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Ooops,something is wrong</title>
</head>
<body>
<p>
    <spring:message code="lbl.somethingiswrong"/>
    <spring:message code="lbl.correctNumber"/>
    <a href="/"><spring:message code="lbl.exit"/></a>
</p>
</body>
</html>
