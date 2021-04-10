<%-- 
    Document   : viewCart
    Created on : Jan 13, 2021, 9:50:44 AM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
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
        <c:if test="${sessionScope.USER.roleID != 'memberGM' && sessionScope.USER.roleID != 'member'}">
            <c:url var="lF" value="MainController">
                <c:param name="btnAction" value="LoginFirst"></c:param>
            </c:url>
            <c:redirect url="${lF}"></c:redirect>
        </c:if>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Hana Shop</a>
                </div>

                <ul class="nav navbar-nav navbar-right">
                    <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.USER.userID}</a></li>
                                <c:url var="logout" value="MainController">
                                    <c:param name="btnAction" value="Logout"></c:param>
                                </c:url>
                    <li><a href="${logout}"><span class="fa fa-sign-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>

        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Buy Confirmation</h1>
            </div>
        </section>
        <c:set var="cart" value="${sessionScope.CART}"></c:set>       
        <c:if test="${cart != null && not empty cart}">
            <c:if test="${cart.getCart().values() != null && not empty cart.getCart().values()}">
                <div class="container mb-4">
                    <div class="row">
                        <div class="col-12">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col"> </th>
                                            <th scope="col">Product Name</th>
                                            <th scope="col" class="text-center">Quantity</th>
                                            <th scope="col" class="text-center">Price</th>
                                            <th scope="col" class="text-right"></th>
                                            <th scope="col" class="text-right">Total</th>
                                            <th> </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="total" value="0"></c:set>
                                        <c:forEach var="dto" items="${cart.getCart().values()}">  
                                            <c:set var="total" value="${total + dto.price * dto.quantity}"></c:set>
                                            <form action="MainController" method="POST">
                                                <tr>
                                                    <td></td>
                                                    <td>${dto.productName}</td>
                                                <td class="text-right">
                                                    <input type="number" name="txtQuantity" value="${dto.quantity}">
                                                </td>
                                                <td class="text-center">
                                                    $${dto.price}
                                                </td>
                                                <td class="text-center">
                                                </td>
                                                <td class="text-right">
                                                    $${dto.price * dto.quantity}
                                                </td>
                                                <td class="text-right">
                                                    <input type="hidden" name="txtProductID" value="${dto.productID}">
                                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}">
                                                    <button type="sumbit" name="btnAction" value="Update" class="btn btn-sm btn-success"><i class="fa fa-wrench"></i> </button>
                                                </td>
                                        </form>

                                        <td>
                                            <c:url var="delete" value="MainController">
                                                <c:param name="txtProductID" value="${dto.productID}"></c:param>
                                                <c:param name="btnAction" value="Delete"></c:param>
                                            </c:url>
                                            <a href="${delete}">
                                                <button class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button>
                                            </a>
                                        </td>
                                        </tr>   
                                    </c:forEach>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><strong>Total</strong></td>
                                        <td class="text-right"><strong>US$${total}</strong></td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col mb-2">
                            <div class="row">
                                <div class="col-sm-12  col-md-6">
                                </div>

                                <div class="col-sm-12 col-md-6 text-right">
                                    <c:url var="save" value="MainController">
                                        <c:param name="btnAction" value="Save"></c:param>
                                        <c:param name="txtTotal" value="${total}"></c:param>
                                    </c:url>

                                    <a  href="${save}">
                                        <button class="btn btn-lg btn-block btn-success text-uppercase">Buy</button>
                                    </a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:if>
        <h1 style="margin-left: 40%; color:green"> ${requestScope.MESSAGE} </h1>
        <c:if test="${requestScope.MESSAGE == null}">
            <h1 style="margin-left: 40%; color:red"> ${requestScope.ERROR} </h1>
        </c:if>
        <c:if test="${(cart.getCart().values() == null || empty cart.getCart().values()) && requestScope.MESSAGE == null}">
            <h1 style="margin-left: 40%; color:red"> Nothing in cart </h1>
        </c:if>
        <div class="col mb-4">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <form action="MainController" method="POST">
                        <button type ="sumbit" name="btnAction" value="ContinueShopping" class="btn btn-block btn-light">Continue Shopping</button>
                    </form>
                </div>
                <div class="col-sm-12  col-md-6">
                    <form action="MainController" method="POST">
                        <button type ="sumbit" name="btnAction" value="ViewHistory" class="btn btn-block btn-light">View History</button>
                    </form>
                </div>
            </div>
        </div>

    </div>

    <!-- Footer -->
    <footer class="text-light">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-lg-4 col-xl-3">
                    <h5>About</h5>
                    <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                    <p class="mb-0">
                        Best food
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
                        <li><i class="fa fa-home mr-2"></i> Hana shop</li>
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
