<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
            <section class="header_text sub">
                <h2><span>YOUR ORDER HISTORY</span></h2>
            </section>
            <section class="main-content">				
                <div class="row">
                    <div class="span12">					
                        <h4 class="title"><span class="text">Order <strong>History</strong></span></h4>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Order Code</th>
                                    <th>Order Date</th>
                                    <th>Sub-Total</th>
                                    <th>Admin Fee</th>
                                    <th>Total</th>
                                    <th>Comments</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${orderList}" varStatus="index">
                                <tr class='clickable-row' data-href='${pageContext.request.contextPath}/order/viewOrder?orderId=${order.id}'>
                                    <td>${order.id}</td>
                                    <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm a" value="${order.orderDate}" /></td>
                                    <td><fmt:formatNumber value="${order.subTotal}" type="currency"/></td>
                                    <td><fmt:formatNumber value="${order.adminFee}" type="currency"/></td>
                                    <td><fmt:formatNumber value="${order.total}" type="currency"/></td>
                                    <td>${order.comments}</td>
                                    <td><a href="${pageContext.request.contextPath}/order/downloadPdf?orderId=${order.id}">Download PDF</a></td>
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
                })
                
              $(".clickable-row").click(function() {
                window.location = $(this).data("href");
                });  
            });
        </script>		
    </body>
</html>