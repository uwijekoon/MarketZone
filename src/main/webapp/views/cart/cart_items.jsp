<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-striped">
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th>Image</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Total</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${cart.itemList}" varStatus="index">
            <tr>
                <td>
                    <!--<button type="button" class="btn btn-danger btn-lg" id="removeItem_${index.index}" onclick="removeCartItem(${index.index})"><span class="glyphicon glyphicon-remove"></span></button>-->
                </td>
                <td><a href="${pageContext.request.contextPath}/ViewProduct?productId=${item.product.id}">
                        <c:choose>
                            <c:when test="${not empty item.product.image}">
                                <img src="${pageContext.request.contextPath}/Images/${item.product.seller.id}/${item.product.image}" class="product-cart-img"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/themes/images/no_image.png" class="product-cart-img"/>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </td>
                <td>${item.product.name}</td>
                <td><input type="text" value="${item.quantity}" class="input-mini" id="item_quantity-${index.index}"></td>
                <td><fmt:formatNumber value="${item.product.unitPrice}" type="currency"/></td>
                <td><fmt:formatNumber value="${item.amount}" type="currency"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><strong><fmt:formatNumber value="${cart.subTotal}" type="currency"/></strong></td>
        </tr>		  
    </tbody>
</table>

<hr>
<p class="cart-total right">
    <strong>Sub-Total</strong>:	<fmt:formatNumber value="${cart.subTotal}" type="currency"/><br>
    <strong>Admin Fee</strong>: <fmt:formatNumber value="${cart.adminFee}" type="currency"/><br>
    <strong>Total</strong>: <fmt:formatNumber value="${cart.total}" type="currency"/><br>
</p>