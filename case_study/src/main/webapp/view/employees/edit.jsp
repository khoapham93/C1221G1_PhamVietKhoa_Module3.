<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Employee</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<c:import url="/components/header.jsp"/>
<c:import url="/components/navbar.jsp"/>
<div class="container-fluid row ">
    <div class="col-5 mx-auto">
        <h2 class="text-center py-1"> Update Employee's information</h2>
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <form method="post">
                <div class="form-group row">
                    <label for="name" class="col-sm-3 col-form-label">Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="name" name="name" value="${employee.name}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("name")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="birthday" class="col-sm-3 col-form-label">Birthday</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="birthday" name="birthday"
                               value="${employee.birthday}">
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
                               value="${employee.idCard}">
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
                               value="${employee.phone}">
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
                               value="${employee.email}">
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
                               value="${employee.address}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("address")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="salary" class="col-sm-3 col-form-label">Salary</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="salary" name="salary"
                               value="${employee.salary}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("salary")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="position" class="col-sm-3 col-form-label">Position</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="position" id="position">
                            <option ${empty employee.positionId ? 'selected' : ''}>Choose position</option>
                            <c:forEach var="position" items="${positions}">
                                <option value="${position.id}" ${employee.positionId eq position.id ? 'selected' : ''}>${position.position}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("position")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="academicLevel" class="col-sm-3 col-form-label">Academic</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="academicLevel" id="academicLevel">
                            <option ${empty employee.academicId ? 'selected' : ''}>Choose Academic level</option>
                            <c:forEach var="academic" items="${academicLevels}">
                                <option value="${academic.id}" ${employee.academicId eq academic.id ? 'selected' : ''}>${academic.level}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("academicLevel")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="department" class="col-sm-3 col-form-label">Department</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="department" id="department">
                            <option ${empty employee.departmentId ? 'selected' : ''}>Choose Department</option>
                            <c:forEach var="department" items="${departments}">
                                <option value="${department.id}" ${employee.departmentId eq department.id ? 'selected' : ''}>${department.department}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("department")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
                        <a class="btn btn-secondary btn-block" role="button" href="/employees">
                            Back to Employee
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
