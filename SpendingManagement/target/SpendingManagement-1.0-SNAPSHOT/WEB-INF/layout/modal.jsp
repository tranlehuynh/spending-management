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
                    <div class="modal-wallet modal-top-item">
                        <span>Choose wallet</span>
                        <div>
                            <img src="${currentUser.avatar}" alt="wallet" id="wallet-modal-img" class="modal-img" />
                            <p class="text-modal-check"><input type="text" placeholder="Wallet" class="wallet-p-input" style="cursor: pointer; pointer-events: none"/></p>
                            <p class="text-modal-check" style="display: none;"><input type="text" path="walletTemp" name="walletTemp" placeholder="Wallet" class="wallet-p-input-none" style="cursor: pointer; pointer-events: none"/></p>

                            <span><i class="fa-solid fa-arrow-right-long"></i></span>
                        </div>
                    </div>
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

<div id="myModal2" class="modal1">
    <!-- Modal content -->
    <div class="modal-content-1">
        <h2 style="margin-bottom: 40px">Select wallet</h2>
        <span class="close2">&times;</span>
        <div class="hehehehe">
            <div class="two-ex-in">
                <div>
                    <div class="income-items haha-items">
                        <c:forEach items="${userWallets}" var="u">
                            <c:if test="${u.userId.id == currentUser.id}">
                                <div class="wallet-modal-div">
                                    <img src="${currentUser.avatar}" alt="avatar">
                                    <span class="wallet-modal-span" id="${u.walletId.id}">${u.walletId.name}</span>
                                </div>
                            </c:if>
                        </c:forEach>             
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="myModal4" class="modal1">
    <!-- Modal content -->
    <div class="modal-content-1">
        <h2 style="margin-bottom: 40px">Select user</h2>
        <span class="close4">&times;</span>
        <div class="hehehehe">
            <div class="two-ex-in">
                <div>
                    <div class="income-items haha-items" style="width: 100%">
                        <div style="display: flex; align-items: center; justify-content: space-around">
                            <c:url value="/dashboard/wallet-user" var="action" />
                            <form:form method="get" action="${action}">
                                <input type="text" name="kw" path="kw" placeholder="Enter email to find user" style="width: 100%; padding: 10px; border: 1px solid black;"/>
                                <button type="submit" style="padding: 10px; outline: none">Search</button>
                            </form:form>
                        </div>                         
                        <c:if test="${userGetByEmail != null}">
                            <div class="wallet-modal-div">
                                <img src="${userGetByEmail.avatar}" alt="avatar">
                                <span class="wallet-modal-span">${userGetByEmail.firstName}</span>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>            



<div id="myModal3" class="modal1">
    <!-- Modal content -->
    <div class="modal-content-1">
        <h2 style="margin-bottom: 30px">Add wallet</h2>
        <span class="close3">&times;</span>
        <div class="hehehehe">
            <div class="">
                <div>
                    <c:url value="/addWallet" var="action" />
                    <form:form method="post" action="${action}" modelAttribute="walletAdd" id="form-wallet-add">
                        <div class="">
                            <div style="width: 100%">
                                <div style="font-size: 16px; text-align: left; margin-bottom: 5px;">Wallet name</div>
                                <input type="text" path="name" name="name" placeholder="Enter wallet name..." style="outline: none; border-radius: 5px; padding: 10px 10px; width: 100%; font-size: 16px; margin-bottom: 15px; border: 1px solid #e4e4e4;"/>
                            </div>
                            <div style="width: 100%">
                                <div style="font-size: 16px; text-align: left; margin-bottom: 5px;">Wallet money</div>
                                <input type="text" path="totalMoney" name="totalMoney" placeholder="Enter wallet money..." style="outline: none; border-radius: 5px; padding: 10px 10px; width: 100%; font-size: 16px; margin-bottom: 15px; border: 1px solid #e4e4e4;"/>
                            </div>
                            <input type="text" path="userWalletTemp" name="userWalletTemp" value="${currentUser.id}" style="display: none;"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="modal-button">
            <div>
                <button type="submit" form="form-wallet-add" id="button-hoho-hehe" class="abutton">Add</button>
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