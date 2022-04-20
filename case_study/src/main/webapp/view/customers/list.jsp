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
<c:import url="/components/header.jsp"/>
<c:import url="/components/navbar.jsp"/>
<div class="container-fluid mx-auto">
    <h3 class="text-center py-1"> Customer list</h3>
    <div class="row justify-content-between">
        <div class="col-4">
            <a class="btn btn-primary" href="/customers?action=create" role="button"> + Create new customer</a>
        </div>
        <div class="col-5">
            <form method="get">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Searching" aria-label="search" name="searchKey">
                    <input type="hidden" name="action" value="search">
                    <div class="input-group-append">
                        <select class="form-control" id="fieldSearch" name="fieldSearch">
                            <option value="name">Name</option>
                            <option value="gender">Gender</option>
                            <option value="phone">Phone</option>
                            <option value="email">Email</option>
                            <option value="birthday">Birthday</option>
                            <option value="customer_type">Customer type</option>
                            <option value="customer_code">Customer code</option>
                            <option value="address">Address</option>
                            <option value="id_card">Id Card</option>
                        </select>
                    </div>
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
            <th scope="col">#</th>
            <th scope="col">Code</th>
            <th scope="col">Name</th>
            <th scope="col">Birthday</th>
            <th scope="col">Gender</th>
            <th scope="col">Id card</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Type</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customerList}" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td> ${customer.customerCode} </td>
                <td>
                    <a href="/customers?action=view&id=${customer.id}">
                            ${customer.name}
                    </a>
                </td>
                <td> ${customer.birthday} </td>
                <td>${ empty customer.gender ? 'Other' : customer.gender == 0 ? 'Female':'Male'}</td>
                <td> ${customer.idCard} </td>
                <td> ${customer.phone} </td>
                <td> ${customer.email} </td>
                <td> ${customer.address} </td>
                <td> ${customer.customerType} </td>
                <td>
                    <a class="btn btn-warning" role="button" href="/customers?action=edit&id=${customer.id}">
                        edit
                    </a>
                </td>
                <td>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"
                            onclick="deleteProduct(
                                <c:out value="${customer.id}"/>,
                                <c:out value="\'${customer.name}\'"/>
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
<c:import url="/components/footer.jsp"/>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete customer</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete customer
                    <span class="text-danger font-weight-bold" id="nameDelete"></span>
                </p>
            </div>
            <div class="modal-footer">
                <div class="mx-auto row">
                    <form action="/customers" method="post">
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
</script>
<script>
    $(document).ready(function () {
        $('#myTable2').DataTable({
            "bFilter": false,
            dom: 'frtlip',
            aLengthMenu: [
                [5, 7, 9, 10, 25, 50, -1],
                [5, 7, 9, 10, 25, 50, "All"]
            ],
            iDisplayLength: 5
        });
    });
</script>
