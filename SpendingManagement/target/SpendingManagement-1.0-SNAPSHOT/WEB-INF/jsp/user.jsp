<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="main-content">
    <div class="user-content">
        <table id="customers">
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.firstName}</td>
                    <td>${u.lastName}</td>
                    <td>${u.email}</td>
                    <td>${u.phone}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="div-pagination" style="margin-top: 20px;">
            <ul class="pagination">
                <c:forEach begin="1" end="${Math.ceil(20/pageSize)}" var="i">
                    <c:url value="/admin/user-manage" var="u">
                        <c:param value="${i}" name="page"/>
                    </c:url>
                    <li style="border-radius: 5px"><a href="${u}">${i}</a></li>                           
                </c:forEach>    
            </ul>
        </div>
    </div>
</div>

