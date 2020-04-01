<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page session="true" %>

<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
<c:url value="/logout" var="logoutUrl"/>
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>
        <spring:message code="lbl.welcome"/> : ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()"> <spring:message code="lbl.logout"/></a>
    </h2>
</c:if>

</body>
</html>