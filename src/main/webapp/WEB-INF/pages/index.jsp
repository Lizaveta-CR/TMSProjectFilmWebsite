<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<body>
<head>
    <title>Shop Page</title>
</head>
<a href="/?lang=en">English</a> || <a href="/?lang=ru">Русский</a>
<h1><security:authorize access="isAnonymous()"><spring:message code="lbl.helloTitle"/></security:authorize></h1>

<security:authorize access="isAnonymous()"><a href="/login"><spring:message code="lbl.helloLogin"/><a
        href="/registration">
        <spring:message code="lbl.helloRegister"/>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_USER')">
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
        <spring:message code="lbl.welcome"/>, ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()"> <spring:message code="lbl.logout"/></a>
    </h2>
    </c:if>
    </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
               <a href="/admin"><spring:message code="lbl.adminButton"/>
            </security:authorize>
</body>
</html>