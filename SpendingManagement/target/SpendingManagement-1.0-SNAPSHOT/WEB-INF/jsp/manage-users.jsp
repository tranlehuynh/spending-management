<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main-content">
    <div class="user-content">
        <c:url value="/admin/user-manage" var="action" />
        <form:form method="post" action="${action}" id="form-active" modelAttribute="activeUser">
            <input type="text" style="display: none;" name="active" id="active-input"/>
            <table id="customers">
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                    <th>Active</th>
                </tr>
                <c:forEach items="${users}" var="u">
                    <c:if test="${u.active == 1 && u.id != 1}">
                        <tr>
                            <td>${u.firstName}</td>
                            <td>${u.lastName}</td>
                            <td>${u.email}</td>
                            <td><button onclick="handleActiveUser(2)" style="text-transform: uppercase; font-size: 14px; cursor: pointer; padding: 5px; color: white; background: red; outline: none; border: none; border-radius: 5px;" type="submit" form="form-active" value="${u.id}" name="id">Lock</button></td>
                        </tr>
                    </c:if>
                    <c:if test="${u.active == 2 && u.id != 1}">
                        <tr>
                            <td>${u.firstName}</td>
                            <td>${u.lastName}</td>
                            <td>${u.email}</td>
                            <td><button onclick="handleActiveUser(1)" style="text-transform: uppercase; font-size: 14px; cursor: pointer; padding: 5px; color: white; background: #2563e9; outline: none; border: none; border-radius: 5px;" type="submit" form="form-active" value="${u.id}" name="id">Unlock</button></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </form:form>
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

