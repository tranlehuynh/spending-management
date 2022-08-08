<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
        <link rel="icon" href="<c:url value="/resources/images/ant.png" />" type="image/gif" sizes="16x16">
    </head>
    <body>
        <div class="container">
            <div class="login-register-form">
                <h3>Log in</h3>
                <form action="" class="login-form">
                    <div class="login-form-email login-form-child">
                        <input type="text" class="login-form-child-input" required>
                        <label for="email" class="login-form-child-label">Your email</label>
                    </div>
                    <div class="login-form-password login-form-child">
                        <input type="password" class="login-form-child-input" required>
                        <label for="password" class="login-form-child-label">Password</label>
                    </div>
                </form>
                <p id="forgot-password"><a href="#"><u>Forgot your password?</u></a></p>
                <div class="login-register-form-button">
                    <div class="account-check">
                        <input type="checkbox">
                        <p>Remember me</p>
                    </div>
                    <button>Log in</button>
                </div>
                <div class="login-black">
                    <p>Don't have any account? <a href="register.html">Create one now</a></p>
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
                <p><a href="#">Read more about this page</a></p>
            </div>
        </div>
        <script src="<c:url value="/resources/js/index.js" />"></script>
    </body>
</html>
