<%--
  Created by IntelliJ IDEA.
  User: vietk
  Date: 4/12/2022
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <style>
        #content{
            width: 450px;
            margin: 0 auto;
            padding: 0 20px 20px;
            background: white;
            border: solid navy 1px;
        }
        #center{
            margin-top: 10px;
            display: flex;

            justify-content: center;
        }
        label{
            width: 10em;
            padding-right: 1em;
            float: left;
        }
    </style>
</head>
<body>
<div id="content">
    <h1> Product Discount Calculator</h1>
    <form action="/product_discount" method="get">
        <div>
            <label for="description"> Product Description</label>
            <input type="text" name="description" id="description">
            <br>
            <label for="price"> Price</label>
            <input type="text" name="price" id="price">
            <br>
            <label for="discount"> Discount percent</label>
            <input type="text" name="discount" id="discount">
        </div>
        <div id ="center">
            <br>
            <input type="submit" value="Discount Product">
        </div>
    </form>
</div>
</body>
</html>
