<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container-1">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="login-register-form">
        <h3>Sign up</h3>
        <c:url value="/register" var="action" />
        <form:form method="post" action="${action}" class="login-form" modelAttribute="user" id="register-form-hiv">
            <div class="register-form-child-input">
                <div>
                    <input type="text" class="register-form-child-input" required name="firstName" path="firstName"/>
                    <label for="firstName" class="login-form-child-label-1">First Name</label>
                </div>
                <div>
                    <input type="text" class="register-form-child-input" name="lastName" path="lastName" required />
                    <label for="firstName" class="login-form-child-label-1">Last name</label>
                </div>
            </div>
            <div class="login-form-email login-form-child">
                <input type="text" class="login-form-child-input" path="email" name="email" required>
                <label for="email" class="login-form-child-label">Email</label>
            </div>
            <c:if test="${errorMessagePassword != null}">
                <div style="background: red; color: white; border-radius: 5px; margin-bottom: 8px; padding: 5px; font-size: 13px">${errorMessagePassword}</div>
            </c:if>
            <div class="login-form-password login-form-child">
                <input type="password" class="login-form-child-input" path="password" name="password" required>
                <label for="password" class="login-form-child-label">Password</label>
            </div>
            <div class="login-form-password login-form-child">
                <input type="password" class="login-form-child-input" path="retypePassword" name="retypePassword" required>
                <label for="password" class="login-form-child-label">Retype password</label>
            </div>
        </form:form>
        <p id="forgot-password"><a href="#"><u>Forgot your password?</u></a></p>
        <div class="login-register-form-button">
            <div class="account-check">
                <input type="checkbox">
                <p>Remember me</p>
            </div>
            <button type="submit" form="register-form-hiv">Create account!</button>
        </div>
        <div class="login-black">
            <p>Already have an account? <a href="${contextPath}/">Log in</a></p>
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
        <p><a href="${contextPath}/register">Read more about this page</a></p>
    </div>
</div>
