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
<!--            <tr>
                <th>Company</th>
                <th>Contact</th>
                <th>Country</th>
            </tr>
            <tr>
                <td>Alfreds Futterkiste</td>
                <td>Maria Anders</td>
                <td>Germany</td>
            </tr>
            <tr>
                <td>Berglunds snabbköp</td>
                <td>Christina Berglund</td>
                <td>Sweden</td>
            </tr>
            <tr>
                <td>Centro comercial Moctezuma</td>
                <td>Francisco Chang</td>
                <td>Mexico</td>
            </tr>
            <tr>
                <td>Ernst Handel</td>
                <td>Roland Mendel</td>
                <td>Austria</td>
            </tr>
            <tr>
                <td>Island Trading</td>
                <td>Helen Bennett</td>
                <td>UK</td>
            </tr>
            <tr>
                <td>Königlich Essen</td>
                <td>Philip Cramer</td>
                <td>Germany</td>
            </tr>
            <tr>
                <td>Laughing Bacchus Winecellars</td>
                <td>Yoshi Tannamuri</td>
                <td>Canada</td>
            </tr>
            <tr>
                <td>Magazzini Alimentari Riuniti</td>
                <td>Giovanni Rovelli</td>
                <td>Italy</td>
            </tr>
            <tr>
                <td>North/South</td>
                <td>Simon Crowther</td>
                <td>UK</td>
            </tr>
            <tr>
                <td>Paris spécialités</td>
                <td>Marie Bertrand</td>
                <td>France</td>
            </tr>-->
        </table>
        <div class="div-pagination">
            <ul class="pagination">
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
            </ul>
        </div>
    </div>
</div>

