<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
</head>
<body>
<c:import url="/components/header.jsp" />
<c:import url="/components/navbar.jsp" />
<div class="container mx-auto">
    <h3 class="text-center py-1"> Customer list</h3>
    <div class="row justify-content-between">
        <div class="col-4">
            <a class="btn btn-primary my-3" href="/customers?action=create" role="button"> + Create new user</a>
        </div>
        <div class="col-4">
            <form method="get">
                <div class="input-group my-3">
                    <input type="text" class="form-control" placeholder="Searching" aria-label="search" name="searchKey">
                    <input type="hidden" name="action" value="search">
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <table id="myTable2" class="table table-striped table-bordered" style="width: 100%">
        <thead class="thead-dark">
        <tr>
            <th scope="col" >#</th>
            <th scope="col">Name</th>
            <th scope="col" >Birthday</th>
            <th scope="col" >Gender</th>
            <th scope="col" >Id card</th>
            <th scope="col" >Phone</th>
            <th scope="col" >Email</th>
            <th scope="col" >Address</th>
            <th scope="col" >Type</th>
            <th scope="col" >Edit</th>
            <th scope="col" >Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td>
                    <a href="/users?action=view&id=${user.getId()}">
                            ${user.getName()}
                    </a>
                </td>
                <td>
                        <%--                    <fmt:setLocale value="vi_VN"/>--%>
                        <%--                    <fmt:formatNumber value="${user.getPrice()}" type="currency"/>--%>
                        ${user.getEmail()}
                </td>
                <td>${user.getCountry()}</td>
                <td>
                    <a class="btn btn-warning" role="button" href="/users?action=edit&id=${user.getId()}">
                        edit
                    </a>
                </td>
                <td>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"
                            onclick="deleteProduct(
                                <c:out value="${user.getId()}"/>,
                                <c:out value="\'${user.getName()}\'"/>
                                    )"
                    >
                        delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test='${message != null}'>
        <small class="text-danger">${message}</small>
    </c:if>
</div>
<c:import url="/components/footer.jsp" />

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete user</h5>
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
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>

<script>
    function deleteProduct(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = " " + name + "?";
    }

    $(document).ready(function() {
        $('#myTable2').DataTable({
            "bFilter": false,
            dom: 'frtlip',
            aLengthMenu: [
                [5, 7, 9, 10, 25, 50, -1],
                [5, 7, 9, 10, 25, 50, "All"]
            ],
            iDisplayLength: 7
        });
    } );
</script>
