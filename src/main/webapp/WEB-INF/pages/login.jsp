<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 29.03.2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>

<html>
<head>
    <title>Login Page</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<h1><spring:message code="lbl.loginTitle"/></h1>

<div id="login-box">

    <h3><spring:message code="lbl.loginWithUsernameAndPassword"/></h3>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty errorMessge}">
        <div class="msg">${errorMessge}</div>
    </c:if>

    <form name='loginForm'
          action="<c:url value='/login' />" method='POST'>

        <table>
            <tr>
                <td><spring:message code="lbl.loginUser"/>:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td><spring:message code="lbl.loginPassword"/>:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="<spring:message code="lbl.submit"/>"/></td>
            </tr>
            <a href="/forgotPass"><spring:message code="lbl.forgotPass"/></a>
        </table>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form>
</div>

</body>
</html>