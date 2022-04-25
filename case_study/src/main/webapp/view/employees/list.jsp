<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee List</title>
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
            <a class="btn btn-primary" href="/employees?action=create" role="button"> + Create new employee</a>
        </div>
        <div class="col-2">
            <button type="button" class="btn btn-primary btn-block " data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Search
                Employee
            </button>
        </div>

    </div>
    <table id="myTable2" class="table table-striped table-bordered" style="width: 100%">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">Salary</th>
            <th scope="col">Position</th>
            <th scope="col">Department</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employeeList}" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td>
                    <a href="/employees?action=view&id=${employee.id}">
                            ${employee.name}
                    </a>
                </td>
                <td> ${employee.phone} </td>
                <td> ${employee.email} </td>
                <td>
                    <fmt:setLocale value="vi_VN"/>
                    <fmt:formatNumber value="${employee.getSalary()}" type="currency"/>
                </td>
                <td> ${employee.position} </td>
                <td> ${employee.department} </td>
                <td>
                    <a class="btn btn-warning" role="button" href="/employees?action=edit&id=${employee.id}">
                        edit
                    </a>
                </td>
                <td>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"
                            onclick="deleteProduct(
                                <c:out value="${employee.id}"/>,
                                <c:out value="\'${employee.name}\'"/>
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
                <h5 class="modal-title">Delete employee</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete employee
                    <span class="text-danger font-weight-bold" id="nameDelete"></span>
                </p>
            </div>
            <div class="modal-footer">
                <div class="mx-auto row">
                    <form action="/employees" method="post">
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
<%--Search--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Search Employee</h5>
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
                                <option value="phone">Phone</option>
                                <option value="email">Email</option>
                                <option value="birthday">Birthday</option>
                                <option value="salary">Salary</option>
                                <option value="position_name">Position</option>
                                <option value="level_name">Academic Level</option>
                                <option value="department_name">Department</option>
                                <option value="address">Address</option>
                                <option value="id_card">Id Card</option>
                            </select>
                        </div>
                        <input type="text" class="form-control col-7" placeholder="Key word 1" aria-label="search" name="searchKey1">
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-5">
                            <select class="form-control" id="fieldSearch2" name="fieldSearch2">
                                <option value="name">Name</option>
                                <option value="phone">Phone</option>
                                <option value="email">Email</option>
                                <option value="birthday">Birthday</option>
                                <option value="salary">Salary</option>
                                <option value="position_name">Position</option>
                                <option value="level_name">Academic Level</option>
                                <option value="department_name">Department</option>
                                <option value="address">Address</option>
                                <option value="id_card">Id Card</option>
                            </select>
                        </div>
                        <input type="text" class="form-control col-7" placeholder="Key word 2" aria-label="search" name="searchKey2">
                    </div>
                    <div class="form-group row">
                        <div class="input-group col-5">
                            <select class="form-control" id="fieldSearch3" name="fieldSearch3">
                                <option value="name">Name</option>
                                <option value="phone">Phone</option>
                                <option value="email">Email</option>
                                <option value="birthday">Birthday</option>
                                <option value="salary">Salary</option>
                                <option value="position_name">Position</option>
                                <option value="level_name">Academic Level</option>
                                <option value="department_name">Department</option>
                                <option value="address">Address</option>
                                <option value="id_card">Id Card</option>
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
