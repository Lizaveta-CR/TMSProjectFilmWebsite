<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 01.04.2020
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Forgot Pass</title>
</head>
<body>
<h3><spring:message code="lbl.forgotPassTitle"/></h3>
<form:form method="POST" modelAttribute="mobile">
    <td><spring:message code="lbl.registerMobile"/>:</td>
    <td><input type='text' name='mobile'></td>
    <input name="<spring:message code="lbl.submit"/>" type="submit"
           value="submit"/></td>
</form:form>
</body>
</html>
