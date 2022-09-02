<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:if test="${loginForm != 1 && loginForm != 2}">
    <nav class="navbar-haha" id="mother-nav">
        <div class="ayda">
            <i class="fa-solid fa-bars nav-icon nav-i-hihi"></i>
        </div>
        <div onclick="window.location.href = '${contextPath}/dashboard';">
            <i class="fa-solid fa-wallet nav-icon"></i>
            <p>My Wallet</p>
        </div>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div onclick="window.location.href = '${contextPath}/account-details';">
                <i class="fa-solid fa-user nav-icon"></i>
                <p>My Account</p>
            </div>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div onclick="window.location.href = '${contextPath}/dashboard/wallet-user';">
                <i class="fa-solid fa-users nav-icon"></i>
                <p>Manage Wallets</p>
            </div>
        </c:if>
        <sec:authorize access="hasAnyAuthority('ADMIN')">
            <div onclick="window.location.href = '${contextPath}/admin/user-manage';">
                <i class="fa-solid fa-user-lock nav-icon"></i>
                <p>Manage Users</p>
            </div>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN')">
            <div onclick="window.location.href = '${contextPath}/report';">
                <i class="fa-solid fa-chart-pie nav-icon"></i>
                <p>Report</p>
            </div>
        </sec:authorize>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <div class="logout-nav" onclick="location.href = '<c:url value="/logout"/>'">
                <i class="fa-solid fa-arrow-right-from-bracket nav-icon"></i>
                <p>Log out</p>
            </div>
        </c:if>
    </nav>
</c:if>


