<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:if test="${loginForm != 1 && loginForm != 2}">
    <header class="header">
        <div class="wallet">
            <span class="nav-i-mobile"><i class="fa-solid fa-bars nav-icon"></i></span>
            <div class="add-model-box">
                <div class="add-model-box-div">
                    <c:forEach items="${userWallets}" var="u">
                        <c:if test="${u.userId.id == currentUser.id}">
                            <c:url value="/dashboard" var="action" />
                            <form:form  method="get" action="${action}" modelAttribute="userWalletaDetails">
                                <div style="display: flex; align-items: center; cursor: pointer; border-radius: 5px;">
                                    <div class="add-model-box-div-child" style="width: 90%; border-radius: 5px;">
                                        <img id="wallet-img" src="https://cdn-icons-png.flaticon.com/512/272/272889.png" alt="wallet" />
                                        <div class="wallet-info">
                                            <p class="wallet-name">${u.walletId.name}</p>                             
                                            <p class="wallet-info-text"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${u.walletId.totalMoney}" /> VND</p>
                                        </div>
                                    </div>
                                    <c:if test="${showWallet1 != 0}">
                                        <div onclick="showModal6(${u.walletId.id})" style="border-radius: 5px; display: flex; align-items: center; justify-content: center; background: red; color: white; font-size: 17px; width: 30px; height: 25px; cursor: pointer;">&times;</div>
                                    </c:if>
                                </div>
                                <input class="this-is-my-input" type="submit" name="wallet" value="${u.walletId.id}" style="display: none"></input>
                            </form:form>
                            <c:url value="/dashboard/deleteWallet" var="action" />
                        </c:if>                    
                    </c:forEach>
                </div>  
                <c:if test="${showNhe == 1}">
                    <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
                    <div class="wallet-info wallet-hehehe" style="pointer-events: none; cursor: auto; display: flex; align-items: center">
                        <p class="wallet-name" style="position: relative; font-size: 15px; box-shadow: 0 3px 7px 0 rgb(0 0 0 / 27%); padding: 4px; border-radius: 10px;">Welcome ${currentUser.firstName} ${currentUser.lastName}</p>
                    </div>
                </c:if>
                <c:if test="${showNha == 2}">
                    <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
                    <div class="wallet-info wallet-hehehe" style="pointer-events: none; cursor: auto; display: flex; align-items: center">
                        <p class="wallet-name" style="position: relative; font-size: 15px; box-shadow: 0 3px 7px 0 rgb(0 0 0 / 27%); padding: 4px; border-radius: 10px;">Welcome ${currentUser.firstName} ${currentUser.lastName}</p>
                    </div>
                </c:if> 
                <c:if test="${showHeader == 3}">
                    <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
                    <div class="wallet-info wallet-hehehe" style="pointer-events: none; cursor: auto; display: flex; align-items: center">
                        <p class="wallet-name" style="position: relative; font-size: 15px; box-shadow: 0 3px 7px 0 rgb(0 0 0 / 27%); padding: 4px; border-radius: 10px;">Welcome ${currentUser.firstName} ${currentUser.lastName}</p>
                    </div>
                </c:if> 
                <c:if test="${showNhe != 1 && showNha != 2 && showHeader != 3}">
                    <img id="wallet-img" src="${currentUser.avatar}" alt="wallet" />
                    <div class="wallet-info wallet-hehehe">
                        <p class="wallet-name" style="position: relative;">${currentUser.firstName}'s Wallet <i class="fa-solid fa-sort-down nav-this-only"></i></p>
                        <p class="wallet-info-text"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${firstWallet}" /> VND</p>
                    </div>
                </c:if>
            </div>
        </div>
        <c:if test="${showNha != 2}">
            <c:if test="${showHeader != 3}">
                <div class="categories">
                    <c:if test="${showNhe == 1}">            
                        <button onclick="handleClickAddUserToWallet()" class="categories-button" id="myBtnUser" style="margin-left: 15px; margin-right: 25px; padding: 13px 40px;">Add user</button>
                    </c:if>
                    <c:if test="${showNhe != 1}">
                        <button class="categories-button" id="myBtnWallet">Add wallet</button>
                        <button onclick="showModal1()" class="categories-button" id="myBtn">Add transaction</button>
                    </c:if>
                </div>
            </c:if>
        </c:if>
    </header>
</c:if>

