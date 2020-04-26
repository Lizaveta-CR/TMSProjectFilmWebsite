<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 26.04.2020
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>Forgot password</title>
</head>
<body>
<h2><spring:message code="lbl.forgotPassTitleChoose"/></h2>
<a href="/forgotPassEmail"><spring:message code="lbl.email"/></a>||<a href="/forgotPassMobile"><spring:message
        code="lbl.registerMobile"/></a>
<a href="/login"><spring:message code="lbl.exit"/></a>
</body>
</html>
