<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 06.04.2020
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
<h1><spring:message code="lbl.Filmpage"/></h1>
<security:authorize access="isAuthenticated()">
    <security:authorize access="hasRole('ROLE_USER') and NOT hasRole('ROLE_ADMIN')">
        <table border="1" style="width:100%">
            <tr>
                <th><spring:message code="lbl.filmName"/></th>
                <th><spring:message code="lbl.filmYear"/></th>
                <th><spring:message code="lbl.filmQuality"/></th>
                <th><spring:message code="lbl.filmTranslation"/></th>
                <th><spring:message code="lbl.filmContinuance"/></th>
                <th><spring:message code="lbl.filmDate"/></th>
            </tr>
            <c:forEach items="${films.list}" var="film">
                <div class="product-preview-container">
                    <tr>
                        <td>${film.name}</td>
                        <td>${film.year}</td>
                        <td>${film.quality}</td>
                        <td>${film.translation}</td>
                        <td>${film.continuance}</td>
                        <td>${film.date}</td>
                    </tr>
                </div>
            </c:forEach>
        </table>
        <a href="/"> <spring:message code="lbl.exit"/></a>
    </security:authorize>
    <security:authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')">
        <table border="1" style="width:100%">
            <tr>
                <th><spring:message code="lbl.filmName"/></th>
                <th><spring:message code="lbl.filmYear"/></th>
                <th><spring:message code="lbl.filmQuality"/></th>
                <th><spring:message code="lbl.filmTranslation"/></th>
                <th><spring:message code="lbl.filmContinuance"/></th>
                <th><spring:message code="lbl.filmDate"/></th>
            </tr>
            <c:forEach items="${films.list}" var="film">
                <div class="product-preview-container">
                    <tr>
                        <td>${film.name}</td>
                        <td>${film.year}</td>
                        <td>${film.quality}</td>
                        <td>${film.translation}</td>
                        <td>${film.continuance}</td>
                        <td>${film.date}</td>
                        <td><a href="/delete/${order.id}"><spring:message code="lbl.delete"/></a></td>
                        <td><a href="/edit-order/${order.id}"><spring:message code="lbl.edit"/></a></td>
                    </tr>
                </div>

            </c:forEach>
        </table>
        <a href="/add-order"><spring:message code="lbl.add"/></a>
        <a href="/"><spring:message code="lbl.exit"/></a>
    </security:authorize>
    <c:if test="${films.totalPages > 1}">
        <div class="page-navigator">
            <c:forEach items="${films.navigationPages}" var="page">
                <c:if test="${page != -1 }">
                    <a href="getAllFilmsFromStore?page=${page}" class="nav-item">${page}</a>
                </c:if>
                <c:if test="${page == -1 }">
                    <span class="nav-item"> ... </span>
                </c:if>
            </c:forEach>
        </div>
    </c:if>
</security:authorize>
</body>
</html>

