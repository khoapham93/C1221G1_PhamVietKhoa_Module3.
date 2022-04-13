<%--
  Created by IntelliJ IDEA.
  User: vietk
  Date: 4/13/2022
  Time: 9:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="container mx-auto">
    <h1> Customer List</h1>
    <table class="table table-striped text-center">
        <thead class="thead-dark">
        <tr>
            <th scope="col"> Name</th>
            <th scope="col"> Birthday</th>
            <th scope="col"> Gender</th>
            <th scope="col"> Grade</th>
            <th scope="col"> Address</th>
            <th scope="col"> Profile image</th>
            <th scope="col"> Customer Type</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customerList}">
            <tr>
                <td>${customer.getName()}</td>
                <td>${customer.getBirthday()}</td>
                <td>
                    <c:set var="gender" value="${customer.getGender()}"/>
                    <c:if test="${gender eq 1}">
                        <c:out value="Nam"/>
                    </c:if>
                    <c:if test="${gender eq 0}">
                        <c:out value="Nữ"/>
                    </c:if>
                </td>
                <td>
                    <c:set var="grade" value="${customer.getGrade()}"/>
                    <c:choose>
                        <c:when test="${grade ge 8.5}">
                            <c:out value="Very good"/>
                        </c:when>
                        <c:when test="${grade ge 7}">
                            <c:out value="Good"/>
                        </c:when>
                        <c:when test="${grade ge 5}">
                            <c:out value="Average"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="Below Average"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${customer.getAddress()}</td>
                <td>
                    <img src="${customer.getProfileImage()}" alt="profile image" width="100">
                </td>
                <td>
                    <c:set var="type" value="${customer.getCustomerType()}"/>
                    <c:forEach var="customerType" items="${customerTypeList}">
                        <c:if test="${type == customerType.getId()}">
                            ${customerType.getCustomerType()}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
