<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header class="header">
    <div class="wallet">
        <span class="nav-i-mobile"><i class="fa-solid fa-align-justify nav-icon"></i></span>
        <div class="add-model-box">
            <div class="add-model-box-div">
                <c:forEach items="${userWallets}" var="u">
                    <div class="add-model-box-div-child">
                        <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
                        <div class="wallet-info">
                            <p class="wallet-name">${u.walletId.name}</p>                             
                            <p class="wallet-info-text"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "20000000" /> VND</p>
                        </div>
                    </div>
                </c:forEach>
            </div>         
            <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
            <div class="wallet-info">
                <p class="wallet-name" style="position: relative;">Wallet name <i class="fa-solid fa-sort-down nav-this-only"></i></p>
                <p class="wallet-info-text"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "20000000" /> VND</p>
            </div>
        </div>
    </div>
    <div class="categories">
        <i class="fa-solid fa-magnifying-glass categories-icon"></i>
        <button class="categories-button" id="myBtn">Add transaction</button>
    </div>
</header>
