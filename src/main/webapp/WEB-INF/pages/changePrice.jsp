<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 23.04.2020
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Change price</title>
</head>
<body>
<form:form action="/admin/changePrice" method="post" modelAttribute="film">
    <spring:bind path="price">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label><spring:message code="lbl.filmPrice"/></label>
            <form:input path="price"/>
            <form:hidden path="film_id"/>
            <form:errors path="price" cssClass="error"></form:errors>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="lbl.submit"/></button>
        </div>
    </spring:bind>
</form:form>
<a href="/getAllFilmsFromStore"><spring:message code="lbl.exit"/></a>
</body>
</html>
