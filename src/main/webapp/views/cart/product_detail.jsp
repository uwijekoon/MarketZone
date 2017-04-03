<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/menu.jsp"></jsp:include>
    <jsp:include page="/views/common/messages.jsp"></jsp:include>
        <section class="header_text sub">
        <h2><span>Product Detail</span></h2>
    </section>
    <section class="main-content">				
        <div class="row">						
            <div class="span12">
                <div class="row">
                    <div class="span4 product-detail-box">
                        <c:choose>
                            <c:when test="${not empty detailProduct.image}">
                                <a href="${pageContext.request.contextPath}/Images/${detailProduct.seller.id}/${detailProduct.image}" class="thumbnail" data-fancybox-group="group1" title="Description 1">
                                <img alt="" src="${pageContext.request.contextPath}/Images/${detailProduct.seller.id}/${detailProduct.image}" class="product-detail-image"></a> 
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/themes/images/no_image.png" class="thumbnail" data-fancybox-group="group1" title="Description 1">
                                <img alt="" src="${pageContext.request.contextPath}/themes/images/no_image.png" class="product-detail-image"></a>            
                            </c:otherwise>
                        </c:choose>
                        <!--<ul class="thumbnails small">								
                            <li class="span1">
                                <a href="${pageContext.request.contextPath}/themes/images/ladies/2.jpg" class="thumbnail" data-fancybox-group="group1" title="Description 2"><img src="themes/images/ladies/2.jpg" alt=""></a>
                            </li>								
                            <li class="span1">
                                <a href="${pageContext.request.contextPath}/themes/images/ladies/3.jpg" class="thumbnail" data-fancybox-group="group1" title="Description 3"><img src="themes/images/ladies/3.jpg" alt=""></a>
                            </li>													
                            <li class="span1">
                                <a href="${pageContext.request.contextPath}/themes/images/ladies/4.jpg" class="thumbnail" data-fancybox-group="group1" title="Description 4"><img src="themes/images/ladies/4.jpg" alt=""></a>
                            </li>
                            <li class="span1">
                                <a href="${pageContext.request.contextPath}/themes/images/ladies/5.jpg" class="thumbnail" data-fancybox-group="group1" title="Description 5"><img src="themes/images/ladies/5.jpg" alt=""></a>
                            </li>
                        </ul>-->
                    </div>
                    <div class="span5">
                        <address>
                            <strong>Seller:</strong> <span>${detailProduct.seller.name}</span><br>
                            <strong>Product Name:</strong> <span>${detailProduct.name}</span><br>
                            <strong>Availability:</strong> 
                            <c:if test="${detailProduct.quantity == 0}">
                                <span class="red_font">Out Of Stock</span><br>
                            </c:if>
                            <c:if test="${detailProduct.quantity != 0}">
                                <span>${detailProduct.quantity} units available</span><br>
                            </c:if>
                        </address>									
                        <h4><strong>Price: <fmt:formatNumber value="${detailProduct.unitPrice}" type="currency"/></strong></h4>
                    </div>
                    <div class="span5">
                        <form class="form-inline" action="${pageContext.request.contextPath}/AddItem" method="post">    
                            <input type="hidden" name="productIndex" value="${productIndex}"/>
                            <p>&nbsp;</p>
                            <label>Qty:</label>
                            <input type="text" class="span1" name="quantity" id="quantity">
                            <button class="btn btn-inverse" type="submit">Add to cart</button>
                        </form>
                    </div>							
                </div>
                <div class="row">
                    <div class="span9">
                        <ul class="nav nav-tabs" id="myTab">
                            <li class="active"><a href="#home">Description</a></li>
                            <li class=""><a href="#profile">Seller Details</a></li>
                        </ul>							 
                        <div class="tab-content">
                            <div class="tab-pane active" id="home">${detailProduct.description}</div>
                            <div class="tab-pane" id="profile">
                                ${detailProduct.seller.description}
                            </div>
                        </div>							
                    </div>						

                </div>
            </div>
        </div>
    </section>			
    <jsp:include page="/views/common/footer.jsp"></jsp:include>
    </div>
    <script src="${pageContext.request.contextPath}/themes/js/common.js"></script>
<script>
    $(function () {
        $('#myTab a:first').tab('show');
        $('#myTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show');
        })
    })
    $(document).ready(function () {
        $('.thumbnail').fancybox({
            openEffect: 'none',
            closeEffect: 'none'
        });

        $('#myCarousel-2').carousel({
            interval: 2500
        });
    });
</script>
</body>
</html>