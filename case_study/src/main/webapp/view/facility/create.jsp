<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Service</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<c:import url="/components/header.jsp"/>
<c:import url="/components/navbar.jsp"/>
<div class="container-fluid row ">
    <div class="col-6 mx-auto">
        <h2 class="text-center py-1"> Create new Service</h2>
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <form method="post">
                <div class="form-group row">
                    <label for="facilityTypeId" class="col-sm-4 col-form-label">Facility type</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="facilityTypeId" id="facilityTypeId" onchange="showFacilityType(this)">
                            <c:forEach var="facilityType" items="${facilityTypeList}">
                                <option value="${facilityType.id}" ${facility.facilityTypeId eq facilityType.id ? 'selected' : ''}>${facilityType.facilityType}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("facilityType")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="code" class="col-sm-4 col-form-label">Service code</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="code" name="code" value="${facility.code}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("code")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="name" class="col-sm-4 col-form-label">Name</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name" value="${facility.name}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("name")}</small>
                        </c:if>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="floorSquare" class="col-sm-4 col-form-label">Floor square</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="floorSquare" name="floorSquare"
                               value="${facility.floorSquare}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("floorSquare")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="rentalFee" class="col-sm-4 col-form-label">Rental cost</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="rentalFee" name="rentalFee"
                               value="${facility.rentalFee}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("rentalFee")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="maximumPeople" class="col-sm-4 col-form-label">Maximum people</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="maximumPeople" name="maximumPeople"
                               value="${facility.maximumPeople}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("maximumPeople")}</small>
                        </c:if>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="rentTypeId" class="col-sm-4 col-form-label">Rent type</label>
                    <div class="col-sm-8">
                        <select class="form-control" name="rentTypeId" id="rentTypeId">
                            <option ${empty facility.rentTypeId ? 'selected' : ''}>Choose Rent type</option>
                            <c:forEach var="rentType" items="${rentTypeList}">
                                <option value="${rentType.id}" ${facility.rentTypeId eq rentType.id ? 'selected' : ''}>${rentType.rentType}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("rentTypeId")}</small>
                        </c:if>
                    </div>
                </div>

                <%-- House and Villa--%>
                <DIV id="villaAndHouseOnly">
                    <div class="form-group row">
                        <label for="roomStandard" class="col-sm-4 col-form-label">Room standard</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="roomStandard" name="roomStandard"
                                   value="${facility.roomStandard}">
                        </div>
                        <div class="col-sm-10">
                            <c:if test='${error != null}'>
                                <small class="text-danger">${error.get("roomStandard")}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="description" class="col-sm-4 col-form-label">Description</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="description" name="description"
                                   value="${facility.description}">
                        </div>
                        <div class="col-sm-10">
                            <c:if test='${error != null}'>
                                <small class="text-danger">${error.get("description")}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="numberFloor" class="col-sm-4 col-form-label">Number of floor</label>
                        <div class="col-sm-8">
                            <input type="number" class="form-control" id="numberFloor" name="numberFloor"
                                   value="${facility.numberFloor}">
                        </div>
                        <div class="col-sm-10">
                            <c:if test='${error != null}'>
                                <small class="text-danger">${error.get("numberFloor")}</small>
                            </c:if>
                        </div>
                    </div>
                </DIV>
                <%-- Villa--%>
                <div class="form-group row" id="villaOnly">
                    <label for="poolSquare" class="col-sm-4 col-form-label">Pool square</label>
                    <div class="col-sm-8">
                        <input type="number" class="form-control" id="poolSquare" name="poolSquare"
                               value="${facility.poolSquare}">
                    </div>
                    <div class="col-sm-10">
                        <c:if test='${error != null}'>
                            <small class="text-danger">${error.get("poolSquare")}</small>
                        </c:if>
                    </div>
                </div>

                <%-- ROOM--%>
                <%--                <div class="form-group row">--%>
                <%--                    <label for="phone" class="col-sm-4 col-form-label">Phone</label>--%>
                <%--                    <div class="col-sm-8">--%>
                <%--                        <input type="number" class="form-control" id="phone" name="phone"--%>
                <%--                               value="${facility.phone}">--%>
                <%--                    </div>--%>
                <%--                    <div class="col-sm-10">--%>
                <%--                        <c:if test='${error != null}'>--%>
                <%--                            <small class="text-danger">${error.get("phone")}</small>--%>
                <%--                        </c:if>--%>
                <%--                    </div>--%>
                <%--                </div>--%>

                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
                        <a class="btn btn-secondary btn-block" role="button" href="/facility">
                            Back to Service
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
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>

<script>

function showFacilityType(selectObject) {
    let selected = selectObject.value;
    if (selected ==3){
        document.getElementById("villaAndHouseOnly").hidden = true;
        document.getElementById("villaOnly").hidden = true;
        $('#villaAndHouseOnly').find("input").each(function () {
            $(this).val('');
        });
        $('#villaOnly').find("input").each(function () {
            $(this).val('');
        });
    }else if(selected ==2){
        document.getElementById("villaAndHouseOnly").hidden = false;
        document.getElementById("villaOnly").hidden = true;
        $('#villaOnly').find("input").each(function () {
            $(this).val('');
        });
    }else {
        document.getElementById("villaAndHouseOnly").hidden = false;
        document.getElementById("villaOnly").hidden = false;
    }
}
$(function() {
    let selected =document.getElementById("facilityTypeId").value;
    if (selected ==3){
        document.getElementById("villaAndHouseOnly").hidden = true;
        document.getElementById("villaOnly").hidden = true;
        $('#villaAndHouseOnly').find("input").each(function () {
            $(this).val('');
        });
        $('#villaOnly').find("input").each(function () {
            $(this).val('');
        });
    }else if(selected ==2){
        document.getElementById("villaAndHouseOnly").hidden = false;
        document.getElementById("villaOnly").hidden = true;
        $('#villaOnly').find("input").each(function () {
            $(this).val('');
        });
    }else {
        document.getElementById("villaAndHouseOnly").hidden = false;
        document.getElementById("villaOnly").hidden = false;
    }
});
</script>
