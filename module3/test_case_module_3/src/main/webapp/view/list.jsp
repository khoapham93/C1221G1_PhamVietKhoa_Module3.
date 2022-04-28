<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Motel</title>
    <%--    offline--%>
    <link rel="stylesheet" href="/components/bootstrap-4.6.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/components/DataTables/DataTables-1.11.5/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <span class="navbar-brand mb-0 h1">Motel manager</span>
    <a class="btn btn-outline-success my-2 my-sm-0" type="button" href="/motels?action=create">
        <i class="fa-solid fa-circle-plus"></i>
        Add new
    </a>
</nav>
<div class="row mx-auto py-2">
    <div class="col-lg-6 col-md-6">
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModalMultiple"
                onclick="deleteMotelMultiple()">
            delete choosen
        </button>
    </div>
    <div class="col-lg-6 col-md-12">
        <form>
            <div class="form-row">
                <div class="col-3">
                    <%--                    <input name="code" type="text" class="form-control" placeholder="Rental code">--%>
                </div>
                <div class="col-4">
                    <input name="name" type="text" class="form-control" placeholder="Name">
                </div>
                <div class="col-3">
                    <input name="phone" type="number" class="form-control" placeholder="Phone">
                </div>
                <div class="col-2">
                    <input type="hidden" name="action" value="search">
                    <button class="btn btn-outline-primary" type="submit" id="button-addon2">
                        <i class="fa-solid fa-magnifying-glass"></i>
                        Search
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="row mx-5">
    <c:if test='${message != null}'>
        <p id="message" class="text-success">${message}</p>
    </c:if>
</div>
<div class="container-fluid mx-auto">
    <table id="myTable2" class="table table-striped table-bordered" style="width: 100%">
        <thead class="thead-dark">
        <tr>
            <th scope="col"></th>
            <th scope="col">#</th>
            <th scope="col">Motel code</th>
            <th scope="col">Name</th>
            <th scope="col">Phone</th>
            <th scope="col">Date start</th>
            <th scope="col">Payment</th>
            <th scope="col">Description</th>
            <th scope="col">Action</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="motel" items="${motelList}" varStatus="index">
            <tr>
                <td class="d-flex justify-content-center">
                    <input type="checkbox" name="checkDelete" value="${motel.id}">

                </td>
                <td>${index.count}</td>
                <td> ${motel.code} </td>
                <td> ${motel.name} </td>
                <td> ${motel.phone} </td>
                <td> ${motel.startDate} </td>
                    <%--                <td>--%>
                    <%--                    <fmt:setLocale value="vi_VN"/>--%>
                    <%--                    <fmt:formatNumber value="${motel.getSalary()}" type="currency"/>--%>
                    <%--                </td>--%>
                <td> ${motel.rentType} </td>
                <td> ${motel.description} </td>
                    <%--                <td>--%>
                    <%--                    <a class="btn btn-warning" role="button" href="/motels?action=edit&id=${motel.id}">--%>
                    <%--                        edit--%>
                    <%--                    </a>--%>
                    <%--                </td>--%>
                <td>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal"
                            onclick="deleteMotel(
                                <c:out value="${motel.id}"/>,
                                <c:out value="\'${motel.name}\'"/>
                                    )">
                        delete
                    </button>
                </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete Motel Rental</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete motel rental have information
                    <span class="text-danger font-weight-bold" id="nameDelete"></span>
                </p>
            </div>
            <div class="modal-footer">
                <div class="mx-auto row">
                    <form action="/motels" method="post">
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

<div class="modal fade" id="deleteModalMultiple" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Delete Motel Rental</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Do you really want to delete motel rental have information
                    <span class="text-danger font-weight-bold" id="nameDeleteMultiple"></span>
                </p>
            </div>
            <div class="modal-footer">
                <div class="mx-auto row">
                    <form action="/motels" method="post">
                        <input type="hidden" name="action" value="deleteMultiple">
                        <input type="hidden" name="idDeleteMultiple" id="idDeleteMultiple">
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
<%--offline--%>
<script src="/components/jquery/jquery-3.5.1.slim.min.js"></script>
<script src="/components/bootstrap-4.6.1-dist/js/bootstrap.bundle.min.js"></script>
<script src="/components/DataTables/DataTables-1.11.5/js/jquery.dataTables.min.js"></script>
<script src="/components/DataTables/DataTables-1.11.5/js/dataTables.bootstrap4.min.js"></script>
<script>
    const element = document.getElementById("message");
    setTimeout(timeoutHidden, 5000)

    function timeoutHidden() {
        element.style.display = 'none'
    }

    function deleteMotel(id, name) {
        document.getElementById("idDelete").value = id;
        document.getElementById("nameDelete").innerText = " " + name + "?";
    }

    function deleteMotelMultiple() {
        let checkboxes = document.getElementsByName("checkDelete");
        let selected = [];

        for (let i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                selected.push(checkboxes[i].value);
            }
        }
        // let param = selected.toString();
        document.getElementById("nameDeleteMultiple").innerText = " " + selected + "?";
        document.getElementById("idDeleteMultiple").value = selected;


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
            iDisplayLength: 10
        });
    });
</script>
