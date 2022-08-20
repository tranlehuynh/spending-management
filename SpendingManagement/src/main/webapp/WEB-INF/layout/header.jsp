<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<header class="header">
    <div class="wallet">
        <span class="nav-i-mobile"><i class="fa-solid fa-align-justify nav-icon"></i></span>
        <div class="add-model-box">
            <div class="add-model-box-div">
                <c:forEach items="${userWallets}" var="u">
                    <c:if test="${u.userId.id == currentUser.id}">
                        <c:url value="/dashboard" var="action" />
                        <form:form  method="get" action="${action}" modelAttribute="userWalletaDetails">
                            <div class="add-model-box-div-child">
                                <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
                                <div class="wallet-info">
                                    <p class="wallet-name">${u.walletId.name}</p>                             
                                    <p class="wallet-info-text"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${u.walletId.totalMoney}" /> VND</p>
                                </div>
                            </div>
                            <input class="this-is-my-input" type="submit" name="wallet" value="${u.walletId.id}" style="display: none"></input>
                        </form:form>
                    </c:if>                    
                </c:forEach>
            </div>         
            <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
            <div class="wallet-info wallet-hehehe">
                <p class="wallet-name" style="position: relative;">${currentUser.firstName} Wallet <i class="fa-solid fa-sort-down nav-this-only"></i></p>
                <p class="wallet-info-text"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${firstWallet}" /> VND</p>
            </div>
        </div>
    </div>
    <div class="categories">
        <i class="fa-solid fa-magnifying-glass categories-icon"></i>
        <button class="categories-button" id="myBtnWallet">Add wallet</button>
        <button class="categories-button" id="myBtnUser" style="display: none; margin-left: 15px; margin-right: 25px; padding: 13px 40px;">Add user</button>
        <button class="categories-button" id="myBtn">Add transaction</button>
    </div>
</header>
