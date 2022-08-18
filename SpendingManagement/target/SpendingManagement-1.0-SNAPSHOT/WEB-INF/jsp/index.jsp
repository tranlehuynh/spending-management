<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="main-content">
    <div class="main-content-child">
        <div class="main-content-header">
            <div>Last month</div>
            <div>This month</div>
            <div>Next month</div>
        </div>
        <div class="text-content">
            <div class="flow">
                <p>Inflow</p>
                <p class="flow-1-p"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "10000000" /> VND</p>
            </div>
            <div class="flow flow-2">
                <p>Outflow</p>
                <p class="flow-2-p"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "20000000" /> VND</p>
            </div>
            <div class="flow-last">
                <p><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "30000000" /> VND</p>
            </div>
            <div class="view-report">
                <a href="#">View report here</a>
            </div>
            <div class="list-items">
                <c:forEach items="${transactions}" var="t">
                    <c:if test="${t.walletId.id == view}">
                        <div class="list-item">
                            <div>
                                <div class="div-list-item-image"><img src="${t.itemId.image}" alt="image" class="list-item-image"></div>
                                <div class="list-item-center">
                                    <div class="list-item-center-h2">${t.itemId.name}</div>
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
                </c:forEach>
                <div class="div-pagination">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${Math.ceil(countTransactions/pageSize)}" var="i">
                            <c:url value="/dashboard" var="u">
                                <c:param value="${i}" name="page"/>
                            </c:url>
                            <li><a href="${u}">${i}</a></li>
                            <div>${view}</div>
                        </c:forEach>                   
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>





