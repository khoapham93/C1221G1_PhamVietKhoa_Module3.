<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<div class="row py-5">
    <div class="col-4 mx-auto">
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <h2 class="text-center py-3"> Update product information</h2>
            <form method="post">
                <div class="form-group">
                    <label for="name">Product name</label>
                    <input type="text" class="form-control" id="name" name="name"
                           value="${product.getName()}" >
                    <c:if test='${error != null}'>
                        <small class="text-danger">${error.get("name")}</small>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" class="form-control" id="price" name="price"
                           value="${product.getPrice()}" >
                    <c:if test='${error != null}'>
                        <small class="text-danger">${error.get("price")}</small>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                           value="${product.getDescription()}" >
                    <c:if test='${error != null}'>
                        <small class="text-danger">${error.get("description")}</small>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="manufacturer">Manufacturer</label>
                    <input type="text" class="form-control" id="manufacturer" name="manufacturer"
                           value="${product.getManufacturer()}" >
                    <c:if test='${error != null}'>
                        <small class="text-danger">${error.get("manufacturer")}</small>
                    </c:if>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
                        <%--                        <button type="submit" class="btn btn-secondary btn-block">Back to Home</button>--%>
                        <a class="btn btn-secondary btn-block" role="button" href="/">
                            Back to Home
                        </a>
                    </div>
                    <div class="col-sm-6 mx-auto">
                        <button type="submit" class="btn btn-primary btn-block">Update</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
