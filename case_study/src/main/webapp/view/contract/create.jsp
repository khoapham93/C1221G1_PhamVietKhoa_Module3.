<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Contract</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<c:import url="/components/header.jsp"/>
<c:import url="/components/navbar.jsp"/>
<div class="container-fluid row ">
    <div class="col-5 mx-auto">
        <h2 class="text-center py-1"> Create new Contract</h2>
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <form method="post">
                <div class="form-group row">
                    <label for="startDate" class="col-sm-3 col-form-label">Start Date</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="startDate" name="startDate" value="${contract.startDate}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("startDate")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="endDate" class="col-sm-3 col-form-label">End Date</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="endDate" name="endDate" value="${contract.endDate}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("endDate")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="deposit" class="col-sm-3 col-form-label">Deposit</label>
                    <div class="col-sm-9">
                        <input type="number" step="any" class="form-control" id="deposit" name="deposit"
                               value="${contract.deposit}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("deposit")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="employeeId" class="col-sm-3 col-form-label">Employee</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="employeeId" id="employeeId">
                            <option ${empty contract.employeeId ? 'selected' : ''}>Choose Employee</option>
                            <c:forEach var="employee" items="${employeeList}">
                                <option value="${employee.id}" ${contract.employeeId eq employee.id ? 'selected' : ''}>${employee.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("employeeId")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="customerId" class="col-sm-3 col-form-label">Customer</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="customerId" id="customerId">
                            <option ${empty contract.customerId ? 'selected' : ''}>Choose Customer</option>
                            <c:forEach var="customer" items="${customerList}">
                                <option value="${customer.id}" ${contract.customerId eq customer.id ? 'selected' : ''}>${customer.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("customerId")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="facilityId" class="col-sm-3 col-form-label">Service</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="facilityId" id="facilityId">
                            <option ${empty contract.facilityId ? 'selected' : ''}>Choose Customer</option>
                            <c:forEach var="facility" items="${facilityList}">
                                <option value="${facility.id}" ${contract.facilityId eq facility.id ? 'selected' : ''}>${facility.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("customerId")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
                        <a class="btn btn-secondary btn-block" role="button" href="/contracts">
                            Back to contract
                        </a>
                    </div>
                    <div class="col-sm-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-block">+ Create</button>
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
