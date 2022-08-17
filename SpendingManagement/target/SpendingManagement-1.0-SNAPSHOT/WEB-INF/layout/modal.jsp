<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <h2 style="margin-bottom: 40px">Add transaction</h2>
        <!-- <span class="close">&times;</span> -->
        <div>
            <c:url value="/transaction" var="action" />
            <form:form method="post" action="${action}" id="form-transaction" modelAttribute="transaction">
                <div class="modal-top">
                    <!--                    <div class="modal-wallet modal-top-item">
                                            <span>Choose wallet</span>
                                            <div>
                                                <img src="./images/wallet.png" alt="wallet" id="wallet-modal-img" class="modal-img" />
                                                <p class="text-modal-check" path="walletTransactionId" >Wallet 1</p>
                                                <span><i class="fa-solid fa-arrow-right-long"></i></span>
                                            </div>
                                        </div>-->
                    <div class="category-wallet modal-top-item">
                        <span>Category</span>
                        <div class="category-wallet-div">
                            <img src="<c:url value="/resources/images/wallet.png" />" alt="category" id="category-modal-img" class="modal-img" />
                            <p class="text-modal-check"><input type="text" path="temp" name="temp" placeholder="Category" class="category-p-input" style="cursor: pointer; pointer-events: none"/></p>
                            <span><i class="fa-solid fa-arrow-right-long"></i></span>
                        </div>
                    </div>
                    <div class="modal-top-item amount-modal">
                        <span>Amount</span>
                        <div><input type="text" placeholder="0" path="amount" name="amount"/></div>
                    </div>
                </div>
                <div class="modal-bottom">
                    <div class="modal-top-item-1 modal-bottom-item-date">
                        <span>Date</span>
                        <div><input type="date" path="date" name="date"/></div>
                    </div>
                    <div class="modal-top-item-1 modal-bottom-item-input amount-modal">
                        <span>Note</span>
                        <div><input type="text" placeholder="Note" path="note" name="note"/></div>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="modal-button">
            <div>
                <button class="cbutton close">Cancel</button>
            </div>
            <div>
                <button type="submit" form="form-transaction" class="abutton">Save</button>
            </div>
        </div>
    </div>
</div>
<div id="myModal1" class="modal1">
    <!-- Modal content -->
    <div class="modal-content-1">
        <h2 style="margin-bottom: 40px">Select category</h2>
        <span class="close1">&times;</span>
        <div class="hehehehe">
            <div class="one-ex-in">
                <div class="expense-button-click">Expense</div>
                <div class="income-button-click">Income</div>
            </div>
            <div class="two-ex-in">
                <div>
                    <div class="expense-items haha-items">
                        <c:forEach items="${items}" var="i">
                            <c:if test="${i.categoryId.id == 1}">
                                <div class="okey-con-de-hihi">
                                    <img src="${i.image}" alt="image">
                                    <span>${i.name}</span>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="income-items haha-items">
                        <c:forEach items="${items}" var="i">
                            <c:if test="${i.categoryId.id == 2}">
                                <div class="okey-con-de-hihi">
                                    <img src="${i.image}" alt="image">
                                    <span>${i.name}</span>
                                </div>
                            </c:if>
                        </c:forEach> 
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>