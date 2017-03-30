<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/menu.jsp"></jsp:include>			
            <section class="header_text sub">
                <img class="pageBanner" src="${pageContext.request.contextPath}/themes/images/pageBanner.png" alt="New products" >
                <h4><span>Shopping Cart</span></h4>
            </section>
            <section class="main-content">				
                <div class="row">
                    <div class="span12">					
                        <h4 class="title"><span class="text"><strong>Your</strong> Cart</span></h4>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Remove</th>
                                    <th>Image</th>
                                    <th>Product Name</th>
                                    <th>Quantity</th>
                                    <th>Unit Price</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${cart.cartItems}" varStatus="index">
                                <tr>
                                    <td><input type="checkbox" value="option1"></td>
                                    <td><a href="${pageContext.request.contextPath}/ViewProduct?productIndex=${item.productIndex}">
                                            <img alt="" src="themes/images/ladies/9.jpg"></a>
                                    </td>
                                    <td>${item.product.name}</td>
                                    <td><input type="text" placeholder="1" class="input-mini"></td>
                                    <td>$${item.product.unitPrice}</td>
                                    <td>$${item.amount}</td>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td><strong>$${cart.totalPrice}</strong></td>
                                </tr>		  
                            </tbody>
                        </table>
           
                        <hr>
                        <p class="cart-total right">
                            <strong>Sub-Total</strong>:	$${cart.totalPrice}<br>
                            <strong>Admin Fee</strong>: $${cart.adminFee}<br>
                           <strong>Total</strong>: $${cart.cartTotal}<br>
                        </p>
                        <hr/>
                        <p class="buttons center">				
                            <button class="btn" type="button">Update</button>
                             <form action="${pageContext.request.contextPath}/" method="post">
                            <button class="btn" type="button">Continue</button>
                             </form>
                            <form action="${pageContext.request.contextPath}/checkout" method="post">
                            <button class="btn btn-inverse" type="submit">Checkout</button>
                            </form>
                        </p>					
                    </div>
                </div>
            </section>			
            <jsp:include page="/views/common/footer.jsp"></jsp:include>
        </div>
        <script src="${pageContext.request.contextPath}/themes/js/common.js"></script>
        <script>
            $(document).ready(function () {
                $('#checkout').click(function (e) {
                    document.location.href = "checkout.html";
                })
            });
        </script>		
    </body>
</html>