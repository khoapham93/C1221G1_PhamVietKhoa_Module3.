<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"--%>
    <%--          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">--%>
    <%--    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">--%>

    <%--    offline--%>
    <link rel="stylesheet" href="/components/bootstrap-4.6.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/components/DataTables/DataTables-1.11.5/css/dataTables.bootstrap4.min.css">

</head>
<body>
<c:import url="/components/header.jsp"/>
<c:import url="/components/navbar.jsp"/>
<div class="container-fluid mx-auto">
    <div class="row justify-content-between py-1">
        <div class="col-4">
            <a class="btn btn-primary" href="/contracts?action=create" role="button"> + Create new contract</a>
        </div>
<%--        <c:if test='${message != null}'>--%>
<%--            <span id="message" class="text-danger col-4">${message}</span>--%>
<%--        </c:if>--%>
        <div class="col-2">
            <a class="btn btn-primary" href="/contracts?action=create" role="button"> + Create new contract details</a>
        </div>
    </div>
    <table id="myTable2" class="table table-striped table-bordered" style="width: 100%">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Start date</th>
            <th scope="col">End date</th>
            <th scope="col">Deposit</th>
            <th scope="col">Employee</th>
            <th scope="col">Customer</th>
            <th scope="col">Service</th>
<%--            <th scope="col">Edit</th>--%>
<%--            <th scope="col">Delete</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contractList}" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td> ${contract.startDate} </td>
                <td> ${contract.endDate} </td>
                <td>
                    <fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${contract.deposit}" type="currency"/>
                </td>
                <td> ${contract.employee} </td>
                <td> ${contract.customer} </td>
                <td> ${contract.facility} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<c:import url="/components/footer.jsp"/>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete contract</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete contract
                    <span class="text-danger font-weight-bold" id="nameDelete"></span>
                </p>
            </div>
            <div class="modal-footer">
                <div class="mx-auto row">
                    <form action="/contracts" method="post">
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

<%--Search modal--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Search contract</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="get">
                <div class="modal-body">
                    <div class="form-group row">
                        <div class="input-group col-5">
                            <select class="form-control" id="fieldSearch1" name="fieldSearch1">
                                <option value="name">Name</option>
                                <option value="gender">Gender</option>
                                <option value="phone">Phone</option>
                                <option value="email">Email</option>
                                <option value="birthday">Birthday</option>
                                <option value="id_card">Id Card</option>
                                <option value="address">Address</option>
                                <option value="customer_code">Customer Code</option>
                                <option value="value">Customer Type</option>
                            </select>
                        </div>
                        <input type="text" class="form-control col-7" placeholder="Key word 1" aria-label="search" name="searchKey1">
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-5">
                            <select class="form-control" id="fieldSearch2" name="fieldSearch2">
                                <option value="name">Name</option>
                                <option value="gender">Gender</option>
                                <option value="phone">Phone</option>
                                <option value="email">Email</option>
                                <option value="birthday">Birthday</option>
                                <option value="id_card">Id Card</option>
                                <option value="address">Address</option>
                                <option value="customer_code">Customer Code</option>
                                <option value="value">Customer Type</option>
                            </select>
                        </div>
                        <input type="text" class="form-control col-7" placeholder="Key word 2" aria-label="search" name="searchKey2">
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-5">
                            <select class="form-control" id="fieldSearch3" name="fieldSearch3">
                                <option value="name">Name</option>
                                <option value="gender">Gender</option>
                                <option value="phone">Phone</option>
                                <option value="email">Email</option>
                                <option value="birthday">Birthday</option>
                                <option value="id_card">Id Card</option>
                                <option value="address">Address</option>
                                <option value="customer_code">Customer Code</option>
                                <option value="value">Customer Type</option>
                            </select>
                        </div>
                        <input type="text" class="form-control col-7" placeholder="Key word 3" aria-label="search" name="searchKey3">
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="hidden" name="action" value="search">
                    <button class="btn btn-outline-primary" type="submit" id="button-addon2">Search</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
<%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"--%>
<%--        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>--%>
<%--<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>--%>

<%--offline--%>
<script src="/components/jquery/jquery-3.5.1.slim.min.js"></script>
<script src="/components/bootstrap-4.6.1-dist/js/bootstrap.bundle.min.js"></script>
<script src="/components/DataTables/DataTables-1.11.5/js/jquery.dataTables.min.js"></script>
<script src="/components/DataTables/DataTables-1.11.5/js/dataTables.bootstrap4.min.js"></script>

<script>
    const element = document.getElementById("message");
    setTimeout(timeoutHidden, 3000)

    function timeoutHidden() {
        element.style.display = 'none'
    }

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
