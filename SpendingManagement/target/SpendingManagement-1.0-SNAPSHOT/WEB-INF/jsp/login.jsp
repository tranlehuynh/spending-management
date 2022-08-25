<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container-1">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="login-register-form">
        <div onclick="googleLoginButton()" style="width: 100%; cursor: pointer; padding: 5px; border-radius: 5px; border: 1px solid rgb(224, 224, 224); display: flex; align-items: center; justify-content: center; margin: auto;" id="google-login">
            <image style="width: 30px;" src="https://play-lh.googleusercontent.com/aFWiT2lTa9CYBpyPjfgfNHd0r5puwKRGj2rHpdPTNrz2N9LXgN_MbLjePd1OTc0E8Rl1"/>
            <a id="google-login-a" style="display: block; font-size: 16px; text-decoration: none; color: black;" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/SpendingManagement&response_type=code
               &client_id=36810703474-djoro0ttsa9girdk8408dfqe76jb4ga9.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>  
        </div>
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
            <button type="submit" value="Submit" id="login-button" form="form1">Log in</button>
        </div>
        <div class="login-black">
            <p>Don't have any account? <a href="${contextPath}/register">Create one now</a></p>
        </div>
    </div>
    <div class="login-register-image">
        <div class="div-images">
            <img class="img1 main-img" src="<c:url value="/resources/images/ao-dai-girl.png" />" alt="Girl">
        </div>
        <h3>Backup your key</h3>
        <h3>Don't risk with you life</h3>
        <p><a href="${contextPath}/">Read more about this page</a></p>
    </div>
</div>


