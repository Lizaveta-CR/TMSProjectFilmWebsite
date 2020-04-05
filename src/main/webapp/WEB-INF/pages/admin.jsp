<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<form action="/admin/getFilmsFromSite" method="GET">
    <label for="filmNum"><spring:message code="lbl.numberOfFilms"/>(1-5):</label>
    <input type="number" id="filmNum" name="filmNum" min="1" max="5">
    <input name="submit" type="submit" value="<spring:message code="lbl.parsePage"/>"/>
</form>
</body>
</html>