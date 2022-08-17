<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container-1">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="login-register-form">
        <h3>Log in</h3>
        <spring:url value="/login" var="action" />
        <form method="post" action="${action}" id="form1" class="login-form">
            <c:if test="${param.error != null}">
                <div style="background: red; color: white; border-radius: 5px; margin-bottom: 8px; padding: 5px; font-size: 13px">Login failed!</div>
            </c:if>
            <div class="login-form-email login-form-child">
                <input name="email" id="email" class="login-form-child-input" required>
                <label for="email" class="login-form-child-label">Your email</label>
            </div>
            <div class="login-form-password login-form-child">
                <input type="password" name="password" id="password" class="login-form-child-input" required>
                <label for="password" class="login-form-child-label">Password</label>
            </div>
        </form>

        <p id="forgot-password"><a href="#"><u>Forgot your password?</u></a></p>
        <div class="login-register-form-button">
            <div class="account-check">
                <input type="checkbox">
                <p>Remember me</p>
            </div>
            <button type="submit" value="Submit" form="form1">Log in</button>
        </div>
        <div class="login-black">
            <p>Don't have any account? <a href="${contextPath}/register">Create one now</a></p>
        </div>
    </div>
    <div class="login-register-image">
        <div class="div-images">
            <img class="img1 main-img" src="<c:url value="/resources/images/ao-dai-girl.png" />" alt="Girl">
            <img class="img2 main-img" src="<c:url value="/resources/images/round-colors.png" />" alt="Image">
            <img class="img1 main-img" src="<c:url value="/resources/images/ao-dai-girl.png" />" alt="Girl">
        </div>
        <h3>Backup your key</h3>
        <h3>Don't risk with you life</h3>
        <p><a href="${contextPath}/">Read more about this page</a></p>
    </div>
</div>


