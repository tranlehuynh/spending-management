<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="main-content">
    <div class="user-content">
        <table id="customers">
            <tr>
                <th>Email</th>
                <th>Wallet</th>
                <th>Note</th>
                <th>Money</th>
                <th>Button</th>
            </tr>
            <c:forEach items="${myTransactions}" var="u">
                <c:url value="/dashboard/wallet-user/transaction" var="action" />
                <form:form method="post" action="${action}" id="form-wallet-user-form0" modelAttribute="walletUserForm">
                    <tr>
                        <c:forEach items="${users}" var="i">
                            <c:if test="${i.id == u.createdUser}">
                                <td>${i.email}</td>
                            </c:if>
                        </c:forEach>                                             
                        <td>${u.walletId.name}</td>
                        <td>${u.note}</td>
                        <td>${u.amount}</td>
                        <td style="display: flex; justify-content: center; align-items: center;">
                            <button style="border: none; background: #2563e9; margin-right: 3px; padding: 5px; color: white; cursor: pointer; font-size: 14px;" id="accept-button" form="form-wallet-user-form0" type="submit" path="walletUserAccept" name="walletUserAccept" value="${u.id}">Accept</button>
                            <button style="border: none; outline: none; background: red; padding: 5px; color: white; cursor: pointer; font-size: 14px;" id="delete-button" form="form-wallet-user-form0" type="submit" path="walletUserDelete" name="walletUserDelete" value="${u.id}">Delete</button>
                        </td>
                    </tr>
                </form:form>
            </c:forEach>  
        </table>
    </div>
</div>

