<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar-haha" id="mother-nav">
    <div class="ayda">
        <i class="fa-solid fa-align-justify nav-icon nav-i-hihi"></i>
    </div>
    <div onclick="window.location.href = '${contextPath}/dashboard';">
        <i class="fa-solid fa-burger nav-icon"></i>
        <p>My Wallet</p>
    </div>
    <div onclick="window.location.href = '${contextPath}/dashboard';">
        <i class="fa-solid fa-sack-dollar nav-icon"></i>
        <p>Spending</p>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div onclick="window.location.href = '${contextPath}/account-details';">
            <i class="fa-solid fa-book nav-icon"></i>
            <p>Account</p>
        </div>
    </c:if>
    <sec:authorize access="hasAnyAuthority('ADMIN')">
        <div onclick="window.location.href = '${contextPath}/admin/user-manage';">
            <i class="fa-solid fa-box nav-icon"></i>
            <p>Manage</p>
        </div>
    </sec:authorize>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div class="logout-nav" onclick="location.href = '<c:url value="/logout"/>'">
            <i class="fa-solid fa-gear nav-icon"></i>
            <p>Log out</p>
        </div>
    </c:if>
</nav>
