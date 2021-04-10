<%-- 
    Document   : manager.jsp
    Created on : Jan 15, 2021, 9:59:02 PM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>Manager Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.USER.roleID != 'admin'}">
            <c:url var="lF" value="MainController">
                <c:param name="btnAction" value="LoginFirst"></c:param>
            </c:url>
            <c:redirect url="${lF}"></c:redirect>
        </c:if>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Hana Shop</a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.USER.roleID}</a></li>
                                <c:url var="logout" value="MainController">
                                    <c:param name="btnAction" value="Logout"></c:param>
                                </c:url>
                    <li><a href="${logout}"><span class="fa fa-sign-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Hana Shop Manager</h1>
            </div>
        </section>
        <h1 class="text-center">   
            <font color="green">${requestScope.MESSAGE}</font>
            <font color="red">${requestScope.ERROR}</font>

        </h1>
        <table class="table table-striped">
            <c:set var="list" value="${sessionScope.LIST}"></c:set>
            <c:set var="productDTO" value="${requestScope.PRODUCT}"></c:set>
                <form action="MainController" method="POST">
                    <tr>
                        <th scope="col">Create Product  <button class="btn btn-sm btn-success" name="btnAction" value="CreateProduct" type="submit">
                                <i class="fa fa-plus" aria-hidden="true"></i> 
                            </button></th>
                    </tr>
                </form>
            </tbody>
        </table>
    <c:if test="${list != null && not empty list}">
        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Product ID</th>
                                    <th scope="col">Product name</th>
                                    <th scope="col">Description</th>
                                    <th scope="col" class="text-right">Quantity</th>
                                    <th scope="col" class="text-right">Price</th>
                                    <th scope="col">Images</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Create time</th>
                                    <th scope="col">Update time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${list}">
                                <form action="UpdateProductController" method="POST" enctype="multipart/form-data">
                                    <tr>
                                        <td>${dto.productID}</td>
                                        <td>
                                            <input class="form-control" type="text" name="txtProductName" value="${dto.productName}">
                                        </td>
                                        <td>
                                            <input class="form-control" type="text" name="txtDescription" value="${dto.description}">
                                        </td>
                                        <td class="text-right">
                                            <input style= "width: 50px" class="text-right" type="number" name="txtQuantity" value="${dto.quantity}">
                                        </td>
                                        <td class="text-right">
                                            <input style= "width: 50px" class="text-right" type="number" name="txtPrice" value="${dto.price}">
                                        </td>
                                        <td style="width: 70px"><img style="width: 70px; height: 70px" src="./images/${dto.image}"/> 
                                            <input type="file" name="txtFile" value="${dto.image}"
                                                   accept=".jpg, .jpeg, .png"></td>
                                        <td>
                                            <c:set var="listStatus" value="${sessionScope.LIST_STATUS}"></c:set>
                                                <select class="form-control ml-3" name="txtStatus" style="width: 150px">
                                                <c:forEach var="status" items="${listStatus}">
                                                    <c:if test="${dto.status == status}">
                                                        <option value="${status}" selected="selected">${status}</option>
                                                    </c:if>
                                                    <c:if test="${dto.status != status}">
                                                        <option value="${status}" >${status}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="text-center" style="width: 100px">
                                            ${dto.createDate}
                                        </td>
                                        <td class="text-center" style="width: 100px">
                                            ${dto.updateDate}
                                        </td>
                                        <td class="text-right">
                                            <button class="btn btn-sm btn-success" type="submit" name="btnAction" value="UpdateProduct">
                                                <i class="fa fa-wrench" aria-hidden="true"></i> 
                                            </button>
                                            <input type="hidden" name="txtProductID" value="${dto.productID}">
                                        </td>
                                </form>
                                <td class="text-right">
                                    <c:url var="delete" value="MainController">
                                        <c:param name="btnAction" value="DeleteProduct"></c:param>
                                        <c:param name="txtProductID" value="${dto.productID}"></c:param>
                                    </c:url>
                                    <a href="${delete}">
                                        <button class="btn btn-sm btn-danger">
                                            <i class="fa fa-trash"></i> 
                                        </button>
                                    </a>
                                </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="col-12">
                            <nav aria-label="...">
                                <ul class="pagination">
                                    <c:forEach var="number" begin="1" end="${requestScope.NUMBER_PAGE}"> 
                                        <li class="page-item">
                                            <c:url var="numberPage" value="MainController">
                                                <c:param name="btnAction" value="NumberPage"></c:param>
                                                <c:param name="txtIndex" value="${number}"></c:param>
                                            </c:url>
                                            <a class="page-link" href="MainController?txtNameProduct=${param.txtNameProduct}&txtMinPrice=${param.txtMinPrice}&txtMaxPrice=${param.txtMinPrice}&txtCategory=${param.txtCategory}&btnAction=Paging&txtIndex=${number}">${number}</a>
                                        </li>
                                    </c:forEach>                                  
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>   
</body>
</html>
