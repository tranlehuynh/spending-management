<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="main-content">
    <div class="main-content-child">
        <c:if test="${showWallet != 0}">
            <div class="main-content-header">
                <div>Last month</div>
                <div>This month</div>
            </div>
        </c:if>
        <div class="text-content">
            <c:if test="${showWallet != 0}">
                <div class="flow">
                    <p>Inflow</p>
                    <p class="flow-1-p"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${inflow}" /> ${currentModel} VND</p>
                </div>
                <div class="flow flow-2">
                    <p>Outflow</p>
                    <p class="flow-2-p"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${outflow}" /> VND</p>
                </div>
                <div class="flow-last">
                    <c:if test="${total < 0}">
                        <p style="color: red; font-size: 18px;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total}" /> VND</p>
                    </c:if>
                    <c:if test="${total > 0}">
                        <p style="color: black; font-size: 18px;"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total}" /> VND</p>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${showWallet == 0}">
                <div style="font-size: 25px; color: red;" class="view-report">
                    <div>You don't have any wallets!</div>
                </div>
            </c:if>
            <c:if test="${showWallet != 0}">
                <div class="view-report">
                    <a href="#">View report here</a>
                </div>
            </c:if>
            <div class="list-items">
                <c:if test="${showWallet != 0}">
                    <c:forEach items="${transactions}" var="t">
                        <c:if test="${t.walletId.id == view}">
                            <c:if test="${t.pending == 1}">
                                <div class="list-item">
                                    <div>
                                        <div class="div-list-item-image"><img src="${t.itemId.image}" alt="image" class="list-item-image"></div>
                                        <div class="list-item-center">
                                            <div class="list-item-center-h2">${t.itemId.name} ${haha}</div>
                                            <div class="list-item-center-text">${t.note}</div>
                                        </div>
                                    </div>
                                    <c:if test="${t.itemId.categoryId.id == 1}">
                                        <div style="color: rgb(245, 89, 89)">
                                            <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "-${t.amount}" /> VND  
                                        </div>
                                    </c:if>    
                                    <c:if test="${t.itemId.categoryId.id == 2}">
                                        <div style="color: #1aa333">
                                            <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${t.amount}" /> VND  
                                        </div>
                                    </c:if>   
                                </div>
                            </c:if>                        
                        </c:if>             
                    </c:forEach>
                </c:if>
                <div class="div-pagination">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${Math.ceil(countTransactionsOfUser/pageSize)}" var="i">
                            <c:url value="/dashboard?wallet=${view}" var="u">
                                <c:param value="${i}" name="page"/>
                            </c:url>
                            <li><a href="${u}">${i}</a></li>                           
                        </c:forEach>                   
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>





