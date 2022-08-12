<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <h2 style="margin-bottom: 40px">Add transaction</h2>
        <!-- <span class="close">&times;</span> -->
        <div>
            <div class="modal-top">
                <div class="modal-wallet modal-top-item">
                    <span>Choose wallet</span>
                    <div>
                        <img src="./images/wallet.png" alt="wallet" id="wallet-modal-img" class="modal-img" />
                        <p class="text-modal-check">Wallet 1</p>
                        <span><i class="fa-solid fa-arrow-right-long"></i></span>
                    </div>
                </div>
                <div class="category-wallet modal-top-item">
                    <span>Category</span>
                    <div>
                        <img src="./images/wallet.png" alt="category" id="category-modal-img" class="modal-img" />
                        <p class="text-modal-check">Category</p>
                        <span><i class="fa-solid fa-arrow-right-long"></i></span>
                    </div>
                </div>
                <div class="modal-top-item amount-modal">
                    <span>Amount</span>
                    <div><input type="text" placeholder="0" /></div>
                </div>
            </div>
            <div class="modal-bottom">
                <div class="modal-top-item-1 modal-bottom-item-date">
                    <span>Date</span>
                    <div><input type="date" /></div>
                </div>
                <div class="modal-top-item-1 modal-bottom-item-input amount-modal">
                    <span>Note</span>
                    <div><input type="text" placeholder="Note" /></div>
                </div>
            </div>
        </div>
        <div class="modal-button">
            <div>
                <button class="cbutton close">Cancle</button>
            </div>
            <div>
                <button class="abutton">Save</button>
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
                            <div>
                                <img src="./images/wallet.png" alt="image">
                                <span>${i.name}</span>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="income-items haha-items">
                        <div>
                            <img src="./images/wallet.png" alt="image">
                            <span>Food</span>
                        </div>
                        <div>
                            <img src="./images/wallet.png" alt="image">
                            <span>Food</span>
                        </div><div>
                            <img src="./images/wallet.png" alt="image">
                            <span>Food</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>