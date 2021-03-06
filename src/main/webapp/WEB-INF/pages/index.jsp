<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<body>
<head>
    <title>Shop Page</title>
    <style>
        p {
            border: 1px solid red;
            padding: 10px;
        }
    </style>
</head>
<security:authorize access="isAnonymous()">
    <a href="${pageContext.request.contextPath}/?lang=en">English</a>&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/?lang=ru">Русский</a>&nbsp;&nbsp;&nbsp;
    <h1><spring:message code="lbl.helloTitle"/></h1>
    <a href="/login"><spring:message code="lbl.helloLogin"/></a>
    <a href="/registration"><spring:message code="lbl.helloRegister"/></a>&nbsp;&nbsp;
</security:authorize>
<security:authorize access="isAuthenticated()">
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
        <h2 style="color:blue;">
            <spring:message code="lbl.welcome"/>, ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> <spring:message code="lbl.logout"/></a>
        </h2>
        <a href="/deleteAccount"><spring:message code="lbl.deleteAccount"/></a>
        <a href="/changePass"><spring:message code="lbl.ChangepasswordTitle"/></a>
    </c:if>
</security:authorize>
<security:authorize access="hasRole('ROLE_USER')">
    <a href="/getAllFilmsFromStore"> <spring:message code="lbl.getFilmsFromStore"/></a>
    <a href="/showUserOrders/${pageContext.request.userPrincipal.name}"> <spring:message code="lbl.myOrders"/></a>
</security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/admin"><spring:message code="lbl.adminButton"></spring:message></a>
</security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN') and not hasRole('ROLE_USER')">
    <a href="/getAllFilmsFromStore"> <spring:message code="lbl.getFilmsFromStore"/></a>
</security:authorize>
</body>
</html>