<%-- 
    Document   : viewDetailHistory
    Created on : Jan 19, 2021, 6:50:36 PM
    Author     : macbook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>View History</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
                <h1 class="jumbotron-heading">Hana Shop</h1>
            </div>
        </section>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <c:set var="list" value="${requestScope.LIST_ORDER_DETAIL}"></c:set>
        <c:if test="${list != null && not empty list}">
            <table class="table table-striped">
                <tr>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col">Product name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                <tr>
                    <c:forEach var="dto" items="${list}">
                        <td></td>
                        <td></td>
                        <td>${dto.productName}</td>
                        <td>${dto.price}</td>
                        <td>${dto.quantity}</td>
                        <td>
                        </td>
                        <td >
                        </td>                       
                        </tbody>
                    </c:forEach>
            </table>
        </c:if>
        <div class="col-sm-12  col-md-6">
            <form action="MainController" method="POST">
                <button type ="sumbit" name="btnAction" value="BackOrders" class="btn btn-block btn-light">Back to orders</button>
            </form>
        </div>
    </body>
</html>
