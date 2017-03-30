<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/menu.jsp"></jsp:include>	
        <section class="main-content">				
            <div class="row">
                <div class="span12">					
                    <h4 class="title"><span class="text">Product <strong>List</strong></span></h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Image</th>
                                <th>Description</th>
                                <th>Unit Price</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${productList}" varStatus="index">
                            <tr class='clickable-row' data-href='${pageContext.request.contextPath}/ViewProductDetail?productId=${product.id}'>
                                <td>${product.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty product.image}">
                                          <img src="${pageContext.request.contextPath}/Images/${sessionScope.user.userId}/${product.image}" class="product-list-img"/>
                                        </c:when>
                                        <c:otherwise>
                                          <img src="${pageContext.request.contextPath}/themes/images/no_image.png" class="product-list-img"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${product.description}</td>
                                <td><fmt:formatNumber value="${product.unitPrice}" type="currency"/></td>
                                <td>${product.quantity}</td>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>


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
        });

        $(".clickable-row").click(function () {
            window.location = $(this).data("href");
        });
    });
</script>		
</body>
</html>