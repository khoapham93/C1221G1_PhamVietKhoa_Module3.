<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Customer</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<c:import url="/components/header.jsp"/>
<c:import url="/components/navbar.jsp"/>
<div class="container-fluid row ">
    <div class="col-5 mx-auto">
        <h2 class="text-center py-1"> Update customer's information</h2>
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <form method="post">
                <div class="form-group row">
                    <label for="name" class="col-sm-3 col-form-label">Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="name" name="name" value="${customer.name}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("name")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="gender" class="col-sm-3 col-form-label">Gender</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="gender" id="gender">
                            <option value="1" ${customer.gender eq 1 ? 'selected' : ''}>Male</option>
                            <option value="0" ${customer.gender eq 0 ? 'selected' : ''}>Female</option>
                            <option value="-1" ${empty customer.gender ? 'selected' : ''}>Other</option>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("gender")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="customerCode" class="col-sm-3 col-form-label">Customer code</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="customerCode" name="customerCode"
                               value="${customer.customerCode}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("customerCode")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="birthday" class="col-sm-3 col-form-label">Birthday</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="birthday" name="birthday"
                               value="${customer.birthday}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("birthday")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="idCard" class="col-sm-3 col-form-label">Id Card</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="idCard" name="idCard"
                               value="${customer.idCard}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("idCard")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-3 col-form-label">Phone</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="phone" name="phone"
                               value="${customer.phone}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("phone")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-3 col-form-label">Email</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="email" name="email"
                               value="${customer.email}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("email")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="address" class="col-sm-3 col-form-label">Address</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="address" name="address"
                               value="${customer.address}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("address")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="type" class="col-sm-3 col-form-label">Customer Type</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="type" id="type">
                            <option ${empty customer.customerType ? 'selected' : ''}>Choose Customer Type</option>
                            <c:forEach var="typeOfCustomer" items="${types}">
                                <option value="${typeOfCustomer.id}" ${customer.customerType eq typeOfCustomer.id ? 'selected' : ''}>${typeOfCustomer.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("type")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
                        <a class="btn btn-secondary btn-block" role="button" href="/customers">
                            Back to Customer
                        </a>
                    </div>
                    <div class="col-sm-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-block">Update</button>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="mx-auto">
                        <c:if test='${message != null}'>
                            <span class="message text-success">${message}</span>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<c:import url="/components/footer.jsp"/>
</body>
</html>
