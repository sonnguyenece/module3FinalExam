<%--
  Created by IntelliJ IDEA.
  User: sonnguyen
  Date: 5/18/2020
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2><a href="/products?action=create">Add New User</a></h2>
    <h2>

        <form method="get">
            <input type="text" name="action" id="productSearchKey" placeholder="Product search key" />
            <input type="submit" value="Search" />
        </form>


    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of products</h2></caption>
        <tr>
            <th>#</th>
            <th>Product Name</th>
            <th> Price</th>
            <th> Quantity</th>
            <th> Color</th>
            <th> Category</th>
            <th colspan="2">Actions</th>
        </tr>

        <c:forEach var="product" items="${listProduct}">
            <tr>

                <td>${product.productId}</td>
                <td>${product.productName}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.category}</td>
                <td><a href="/products?action=edit&id=${product.productId}">Edit</a></td>
                <td><a href="/products?action=delete&id=${product.productId}">Delete</a></td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>

