<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<div class="row py-5">
    <div class="col-4 mx-auto">
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <h2 class="text-center py-3"> Create new product</h2>
            <form method="post">
                <div class="form-group">
                    <label for="name">Product name</label>
                    <input type="text" class="form-control" id="name" name="name">
                    <c:if test='${error != null}'>
                        <p class="text-danger">${error.get("name")}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" class="form-control" id="price" name="price">
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" class="form-control" id="description" name="description">
                </div>
                <div class="form-group">
                    <label for="manufacturer">Manufacturer</label>
                    <input type="text" class="form-control" id="manufacturer" name="manufacturer">
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mx-auto">
<%--                        <button type="submit" class="btn btn-secondary btn-block">Back to Home</button>--%>
                        <a class="btn btn-secondary btn-block" role="button" href="/">
                            Back to Home
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

</body>
</html>
