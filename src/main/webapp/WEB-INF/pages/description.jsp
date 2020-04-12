<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 12.04.2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Description</title>
</head>
<body>
<security:authorize access="hasRole('ROLE_USER')">
    ${filmDescription}
</security:authorize>
</body>
</html>
