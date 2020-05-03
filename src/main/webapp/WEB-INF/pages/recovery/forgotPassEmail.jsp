<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 26.04.2020
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Forgot pass email</title>
</head>
<body>
<h3><spring:message code="lbl.forgotPassTitle"/></h3>
<form:form method="POST" modelAttribute="email">
    <td><spring:message code="lbl.email"/>:</td>
    <td><input type='text' name='email'></td>
    <input name="<spring:message code="lbl.submit"/>" type="submit" value="submit"/></td>
</form:form>
<a href="/"><spring:message code="lbl.exit"/></a>
</body>
</html>
