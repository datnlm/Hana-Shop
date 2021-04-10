<%-- 
    Document   : shopping
    Created on : Jan 6, 2021, 6:54:21 PM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/styleViewCart.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    </head>
    <body>
        <c:if test="${sessionScope.USER.roleID == 'admin'}">
            <c:url var="lF" value="MainController">
                <c:param name="btnAction" value="LoginFirst"></c:param>
            </c:url>
            <c:redirect url="${lF}"></c:redirect>
        </c:if>
        <c:set var="listProduct" value="${sessionScope.LIST_PRODUCT}"></c:set> 
        <c:set var="listCategory" value="${sessionScope.LIST_CATEGORY}"></c:set>
            <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <c:url var="homePage" value="MainController">
                <c:param name="btnAction" value="HomePage"></c:param>
            </c:url>
            <div class="container">               
                <a class="navbar-brand" href="${homePage}">Hana Shop</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <form action="MainController" method="get" class="navbar-nav m-auto">
                            <li class="nav-item">                     
                                <input type="text" class="form-control ml-3" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Name..." name="txtNameProduct" value="${param.txtNameProduct}">
                            </li>
                            <li style="margin-left: 10px" class="nav-item">                     
                                <input style="width: 150px" type="number" class="form-control ml-3" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Price from..." name="txtMinPrice" value="${param.txtMinPrice}">
                            </li>
                            <li class="nav-item">                     
                                <input style="width: 150px" type="number" class="form-control ml-3" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="to..."  name="txtMaxPrice" value="${param.txtMaxPrice}">
                            </li>
                            <li class="nav-item">
                                <c:set var="selectedCategory" value="${sessionScope.CATEGORY}"></c:set>
                                    <select class="form-control ml-3" name="txtCategory">
                                        <option value="">Category</option>
                                    <c:forEach var="category" items="${listCategory}">
                                        <c:if test="${selectedCategory == category}">
                                            <option selected="selected">${category}</option>
                                        </c:if>
                                        <c:if test="${selectedCategory != category}">
                                            <option>${category}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </li>
                            <li clas="nav-item">
                                <div class="input-group-append">
                                    <button type="sumbit" class="btn btn-secondary btn-number ml-3" name="btnAction" value="Search">
                                        <i class="fa fa-search"></i>
                                    </button>

                                </div>
                            </li>
                        </form>
                        <form class="form-inline my-2 my-lg-0">
                            <c:url var="viewCart" value="MainController">
                                <c:param name="btnAction" value="viewCart"></c:param>
                            </c:url>
                            <a class="btn btn-success btn-sm ml-3" href="${viewCart}">
                                <i class="fa fa-shopping-cart"></i> Cart

                            </a>
                        </form>
                        <c:if test="${sessionScope.USER != null}">

                            <c:url var="logout" value="MainController">
                                <c:param name="btnAction" value="Logout"></c:param>
                            </c:url>
                            <a style="color: white; width: 70px; text-align: center; margin-left: 20px; padding-top: 8px">${sessionScope.USER.userID}</a>
                            <a class="btn btn-danger btn-sm ml-3" href="${logout}">  <i class="fa fa-sign-out"></i>Logout</a>
                        </c:if>
                        <c:if test="${sessionScope.USER == null}">
                            <c:url var="login" value="MainController">
                                <c:param name="btnAction" value="Login"></c:param>
                            </c:url>
                            <a class="btn btn-success btn-sm ml-3" href="${login}">  <i class="fa fa-sign-login"></i>Login</a>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <img class="card-img-top" src="./images/—Pngtree—cartoon creative takeaway food banner_1069797.jpg" alt="banner">
            </div>
        </section>
        <h1 style="margin-left: 20%; color:red">${requestScope.ERROR}</h1>
        <c:if test="${listProduct == null || empty listProduct}">
            <h1 style="margin-left: 40%; color:red">Nothing</h1>
        </c:if>
        <c:if test="${listProduct != null && not empty listProduct}">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="row">
                            <c:forEach var="dto" items="${listProduct}">
                                <div class="col-3">
                                    <div class="card">
                                        <form action="MainController" method="GET">
                                            <img style="width: 225px;height: 225px" class="card-img-top" src="./images/${dto.image}" alt="Card image cap">
                                            <div class="card-body">
                                                <h4 class="card-title">${dto.productName}</h4>
                                                <p class="card-text">${dto.description}</p>
                                                <p class="card-text">Available in product: ${dto.quantity}</p>
                                                <div class="row">
                                                    <div class="col">
                                                        <p class="btn btn-danger btn-block">${dto.price}$</p>
                                                    </div>
                                                    <div class="col">
                                                        <button type ="sumbit" name="btnAction" value="Add" class="btn btn-success btn-block">Add to cart</button>
                                                        <input type="hidden" name="txtProductID" value="${dto.productID}">
                                                        <input type="hidden" name="txtProductName" value="${dto.productName}">
                                                        <input type="hidden" name="txtPrice" value="${dto.price}">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>                                 
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="col-12">
                                <nav aria-label="...">
                                    <ul class="pagination">
                                        <c:forEach var="number" begin="1" end="${requestScope.NUMBER_PAGE}"> 
                                            <li class="page-item">
                                                <c:url var="numberPage" value="MainController">
                                                    <c:param name="btnAction" value="NumberPage"></c:param>
                                                    <c:param name="txtIndex" value="${number}"></c:param>
                                                </c:url>
                                                <a class="page-link" href="MainController?txtNameProduct=${param.txtNameProduct}&txtMinPrice=${param.txtMinPrice}&txtMaxPrice=${param.txtMinPrice}&txtCategory=${param.txtCategory}&btnAction=Search&txtIndex=${number}">${number}</a>
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
        <!-- Footer -->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-lg-4 col-xl-3">
                        <h5>About</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <p class="mb-0">
                            Hana Shop
                        </p>
                    </div>
                    <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                        <h5>Informations</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li>Datnlm</li>
                            <li>SE140870</li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">                                            
                    </div>
                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Contact</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-home mr-2"></i> Hana Shop</li>
                            <li><i class="fa fa-envelope mr-2"></i> datnlm@fpt.com</li>
                            <li><i class="fa fa-phone mr-2"></i> + 33 12 14 15 16</li>
                            <li><i class="fa fa-print mr-2"></i> + 33 12 14 15 16</li>
                        </ul>
                    </div>                   
                </div>
            </div>
        </footer>
    </body>
</html>
