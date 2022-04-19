<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User detail</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<div class="row py-5">
    <div class="col-4 mx-auto">
        <div class="container mx-auto shadow-lg p-3 mb-5 bg-white rounded-lg">
            <h2 class="text-center py-3"> User information</h2>
            <form>
                <div class="form-group row">
                    <label class="col-sm-3" for="id">User id</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="id" name="id"
                               value="${user.getId()}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3" for="name">User name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="name" name="name"
                               value="${user.getName()}" readonly>
                    </div>
                </div>
<%--                <div class="form-group row">--%>
<%--                    <label class="col-sm-3" for="price">Price</label>--%>
<%--                    <div class="col-sm-9">--%>
<%--                        <input type="number" class="form-control" id="price" name="price"--%>
<%--                               value="${user.getPrice()}" readonly>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="form-group row">
                    <label class="col-sm-3" for="email">Email</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="email" name="email"
                               value="${user.getEmail()}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-3" for="country">Country</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="country" name="country"
                               value="${user.getCountry()}" readonly>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-sm-4 mx-auto">
                        <a class="btn btn-secondary btn-block" role="button" href="/">
                            Back to Home
                        </a>
                    </div>
                    <div class="col-sm-4 mx-auto">
                        <a class="btn btn-warning btn-block" role="button" href="/users?action=edit&id=${user.getId()}">
                            edit
                        </a>
                    </div>
                    <div class="col-sm-4 mx-auto">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#deleteModal"
                                onclick="deleteProduct(
                                <c:out value="\'${user.getId()}\'"/>,
                                <c:out value="\'${user.getName()}\'"/>
                                        )"
                        >
                            delete
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete User</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete user
                    <span class="text-danger font-weight-bold" id="nameDelete"></span>
                </p>
            </div>
            <div class="modal-footer">
                <div class="mx-auto row">
                    <form action="/users" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" id="idDelete">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
<script>
    function deleteProduct(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = " " + name + "?";
    }
</script>