<%-- 
    Document   : create
    Created on : Mar 16, 2021, 2:35:11 PM
    Author     : macbook
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <table class="table table-striped">
            <c:set var="list" value="${sessionScope.LIST}"></c:set>
            <c:set var="productDTO" value="${requestScope.PRODUCT}"></c:set>
                <tr>
                    <th scope="col">Product ID</th>
                    <th scope="col">Product name</th>
                    <th scope="col">Description</th>
                    <th scope="col" class="text-right">Quantity</th>
                    <th scope="col" class="text-right">Price</th>
                    <th scope="col">Images</th>
                    <th scope="col">Category</th>
                    <th scope="col"></th>
                </tr>

                <form action="CreateProductController" method="POST" enctype="multipart/form-data">
                    <tr>
                        <td>
                            <input class="form-control" type="text" name="txtProductID" value="${productDTO.productID}">
                    </td>
                    <td>
                        <input class="form-control" type="text" name="txtProductName" value="${productDTO.productName}">
                    </td>
                    <td>
                        <input class="form-control" type="text" name="txtDescription" value="${productDTO.description}">
                    </td>
                    <td class="text-right">
                        <input class="text-right" type="number" name="txtQuantity" value="${productDTO.quantity}">
                    </td>
                    <td class="text-right">
                        <input class="text-right" type="number" name="txtPrice" value="${productDTO.price}">
                    </td>
                    <td>
                        <input type="file" name="txtFile" value="${productDTO.image}"
                               accept=".jpg, .jpeg, .png"></td>
                    <td>
                        <c:set var="listCategory" value="${sessionScope.LIST_CATEGORY}"></c:set>
                            <select class="form-control ml-3" name="txtCategory" style= "width: 120px">
                                <option value="Category">Category</option>
                            <c:forEach var="category" items="${listCategory}">
                                <c:if test="${productDTO.category == category}">
                                    <option selected="selected">${category}</option>
                                </c:if>
                                <c:if test="${productDTO.category != category}">
                                    <option>${category}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="text-right">
                        <button class="btn btn-sm btn-success" type="submit">
                            <i class="fa fa-plus" aria-hidden="true"></i> 
                        </button>
                    </td>
            </form>
        </tbody>
    </table>
    <h1 class="text-center">   
        <font color="red">${requestScope.ERROR}</font>
    </h1>
    <c:url var="back" value="MainController">
        <c:param name="btnAction" value="BackToHome"></c:param>
    </c:url>

    <a  href="${back}">
        <button class="btn btn-lg btn-block btn-light text-uppercase">Back to manager</button>
    </a>
</body>
</html>
