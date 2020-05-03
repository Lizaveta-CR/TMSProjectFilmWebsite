<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 26.04.2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Email confirmation pass</title>
</head>
<body>
<label><spring:message code="lbl.emailConfirmationPassTitle"/></label>
<spring:message code="lbl.email"/>: ${email}
<form action="/forgotPassEmailConfirm" method="GET">
    <input type="number" id="code" name="code">
    <input type="hidden" name="email" value="${email}">
    <input name="submit" type="submit" value="<spring:message code="lbl.submit"/>"/>
</form>
</body>
</html>
