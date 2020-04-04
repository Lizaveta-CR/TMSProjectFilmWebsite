<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 29.03.2020
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create an account</title>
<%--    <link href="src/main/webapp/WEB-INF/resources/css/common.css" rel="stylesheet">--%>
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading"><spring:message code="lbl.registerCreateAccount"/></h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label><spring:message code="lbl.loginUser"/>:</label>
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <form:errors path="username" cssClass="error" cssStyle="animation: alternate"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label><spring:message code="lbl.loginPassword"/>:</label>
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="mobile">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label><spring:message code="lbl.registerMobile"/>:</label>
                <form:input type="text" path="mobile" class="form-control" placeholder="mobile"
                            autofocus="true"></form:input>
                <form:errors path="mobile"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="lbl.submit"/></button>
    </form:form>

</div>

</body>
</html>
