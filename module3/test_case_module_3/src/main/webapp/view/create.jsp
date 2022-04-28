<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Motel Rental</title>
    <link rel="stylesheet" href="/components/bootstrap-4.6.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
<div class="container-fluid row ">
    <div class="col-5 mx-auto">
        <h2 class="text-center py-1"> Create new Motel Rental</h2>
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <form method="post">
                <div class="form-group row">
                    <label for="name" class="col-sm-3 col-form-label">Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="name" name="name" value="${motel.name}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("name")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-3 col-form-label">Phone</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="phone" name="phone"
                               value="${motel.phone}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("phone")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="startDate" class="col-sm-3 col-form-label">Start date</label>
                    <div class="col-sm-9">
                        <input type="date" class="form-control" id="startDate" name="startDate"
                               value="${motel.startDate}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("startDate")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="rentTypeId" class="col-sm-3 col-form-label">Payment</label>
                    <div class="col-sm-9">
                        <select class="form-control" name="rentTypeId" id="rentTypeId">
                            <option ${empty motel.rentTypeId ? 'selected' : ''}>Choose Payment</option>
                            <c:forEach var="rentType" items="${rentTypeList}">
                                <option value="${rentType.id}" ${motel.rentTypeId eq rentType.id ? 'selected' : ''}>${rentType.rentType}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("rentTypeId")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="description" class="col-sm-3 col-form-label">Description</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="description" name="description"
                               value="${motel.description}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("description")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
                        <a class="btn btn-secondary btn-block" role="button" href="/motels">
                            Back to motel manager
                        </a>
                    </div>
                    <div class="col-sm-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-block">
                            <i class="fa-solid fa-circle-plus"></i>
                            Create
                        </button>
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

</body>
</html>
<script src="/components/jquery/jquery-3.5.1.slim.min.js"></script>
<script src="/components/bootstrap-4.6.1-dist/js/bootstrap.bundle.min.js"></script>