<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="./head.jsp"></jsp:include>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
        <spring:url value="/" var="basedURL" />
        <spring:url value="/themes/images" var="themeImg" />
    </head>
    <body>		
        <div id="top-bar" class="container">
            <div class="row">
                <div class="span4">
                    <!--<form method="POST" class="search_form">
                        <input type="text" class="input-block-level search-query" Placeholder="eg. T-sirt">
                    </form>-->
                    <c:if test="${sessionScope.user.userType == 2}">
                        <a href="${basedURL}seller/productList" class="logo pull-left"><img src="${themeImg}/logo.png" class="site_logo" alt=""></a>
                    </c:if>
                    <c:if test="${sessionScope.user.userType != 2}">
                        <a href="${basedURL}" class="logo pull-left"><img src="${themeImg}/logo.png" class="site_logo" alt=""></a>
                    </c:if>
                    
                </div>
                <div class="span8">
                    <div class="account pull-right">

                        <ul class="user-menu">	
                            <c:if test="${sessionScope.user != null}"><li>Welcome ${sessionScope.user.firstName} ${sessionScope.user.lastName} </li>
                                <!--<li><a href="#">My Account</a></li>-->
                                    <c:if test="${sessionScope.user.userType == 1}">
                                    <li><a href="${basedURL}cart/viewCart">Your Cart</a></li>
                                    <li><a href="${basedURL}order/viewOrderHistory">Order History</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.user.userType == 2}">
                                    <li><a href="${basedURL}seller/productList">Products</a></li>
                                    <!--<li><a href="${basedURL}/ViewOrders">Order List</a></li>-->
                                    <li><a href="${basedURL}seller/addProduct">Add Product</a></li>
                                     <li><a href="${basedURL}order/viewOrderRequests">Order Requests</a></li>
                                    </c:if>
                                <li><a href="${basedURL}user/logout">Logout</a></li>

                            </c:if>
                            <c:if test="${sessionScope.user == null}">
                            	<li><a href="${basedURL}user/login">Login/Register</a></li>
                            	<li><a href="${basedURL}cart/viewCart">Your Cart</a></li>
                            </c:if>

                        </ul>
                    </div>
                </div>

            </div>
        </div>
