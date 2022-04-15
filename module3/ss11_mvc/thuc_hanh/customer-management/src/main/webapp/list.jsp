<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<div class="container mx-auto text-center">
    <h1>Customer List</h1>
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customers}" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td>
                    <a href="/customers?action=view&id=${customer.getId()}">
                            ${customer.getName()}
                    </a>
                </td>

                <td>${customer.getEmail()}</td>
                <td>${customer.getAddress()}</td>
                <td>
                    <a href="/customers?action=edit&id=${customer.getId()}">
                        edit
                    </a>
                </td>
                <td>
                    <a href="/customers?action=delete&id=${customer.getId()}">
                        delete
                    </a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<div class="container">
    <a class="btn btn-primary" href="/customers?action=create" role="button">Create new customer</a>
</div>
</body>
</html>
