<%--
  Created by IntelliJ IDEA.
  User: vietk
  Date: 4/13/2022
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple models.Calculator</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1> Simple calculator</h1>
    <form action="/calculator" method="get">
        <table >
            <tr>
                <td>
                    <label for="firstNumber"> First operand: </label>
                </td>
                <td>
                    <input id="firstNumber" type="number" name="firstNumber" placeholder="First number">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="operator">Operator</label>

                </td>
                <td>
                    <select name="operator" id="operator">
                        <option value="choose">choose</option>
                        <option value="+">Addition</option>
                        <option value="-">Subtraction</option>
                        <option value="*">Multiplication</option>
                        <option value="/">Division</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="secondNumber"> Second operand: </label>
                </td>
                <td>
                    <input id="secondNumber" type="number" name="secondNumber" placeholder="Second number">
                </td>
            </tr>

        </table>
        <input type="submit" value="Calculate">
    </form>
    <c:out value="${result}">
    </c:out>

</div>

</body>
</html>
