<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
        <section class="header_text sub">
            <h2><span>Shopping Cart</span></h2>
        </section>
        <section class="main-content">				
            <div class="row">
                <div class="span12">					
                    <h4 class="title"><span class="text"><strong>Your</strong> Cart</span></h4>
                    <div id="cart_items">
                    <c:if test="${fn:length(cart.itemList) eq 0}">
                        <h2 class="center">No items in the Cart</h2>
                    </c:if>
                    <c:if test="${fn:length(cart.itemList) gt 0}">
                        <jsp:include page="/views/cart/cart_items.jsp" ></jsp:include>
                    </c:if>

                </div>
                <hr/>
                <p class="buttons center">
                    <button class="btn" type="button" id="conShopping">Continue Shopping</button>
                    <c:if test="${fn:length(cart.itemList) gt 0}">
                        <button class="btn" type="button" onclick="updateCart()">Update</button>
                        <button class="btn btn-inverse" type="button" id="checkout">Checkout</button>
                    </c:if>
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
                                    document.location.href = "${pageContext.request.contextPath}/cart/checkout";
                                });
                            });

                            $(document).ready(function () {
                                $('#conShopping').click(function (e) {
                                    document.location.href = "${pageContext.request.contextPath}";
                                });
                            });
</script>		
</body>
</html>