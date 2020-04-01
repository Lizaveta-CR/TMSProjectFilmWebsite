<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<body>
<h1>HTTP Status 403 - <spring:message code="lbl.accessdenied"/></h1>

<c:choose>
    <c:when test="${empty username}">
        <h2><spring:message code="lbl.403nopermission"/></h2>
    </c:when>
    <c:otherwise>
        <h2><spring:message code="lbl.loginUser"/> : ${username} <br/>
            <h2><spring:message code="lbl.403nopermission"/></h2></h2>
    </c:otherwise>
</c:choose>

</body>
</html>