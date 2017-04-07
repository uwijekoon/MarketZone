<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/messages.jsp"></jsp:include>
        <section class="header_text sub">
        <h4 class="product-title title"><span class="line"><strong>${cartItemForm.product.name}</strong></span></h4>
    </section>
    <section class="main-content">				
        <div class="row">						
            <div class="span12">
                <div class="row">
                    <div class="span4 product-detail-box">
                        <c:choose>
                            <c:when test="${not empty cartItemForm.product.image}">
                                <a href="${pageContext.request.contextPath}/image/${cartItemForm.product.seller.id}/${cartItemForm.product.image}" class="thumbnail" data-fancybox-group="group1" title="${cartItemForm.product.name}">
                                <img alt="" src="${pageContext.request.contextPath}/image/${cartItemForm.product.seller.id}/${cartItemForm.product.image}" 
                                class="product-detail-image"></a> 
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
                            <strong>Seller:</strong> <span>${cartItemForm.product.seller.name}</span><br>
                            <strong>Product Name:</strong> <span>${cartItemForm.product.name}</span><br>
                            <strong>Availability:</strong> 
                            <c:if test="${cartItemForm.product.quantity == 0}">
                                <span class="red_font">Out Of Stock</span><br>
                            </c:if>
                            <c:if test="${cartItemForm.product.quantity != 0}">
                                <span>${cartItemForm.product.quantity} units available</span><br>
                            </c:if>
                        </address>									
                        <h4><strong>Price: <fmt:formatNumber value="${cartItemForm.product.unitPrice}" type="currency"/></strong></h4>
                    </div>
                    <div class="span5">
                        <form:form action="${basedURL}cart/addItem" method="post"  commandName="cartItemForm">  
                        	<form:input type="hidden" path="product.id" id="productId" class="input-xlarge" value="${product.id}"/>  
                            <p>&nbsp;</p>
                            <label>Quantity:</label>
                            <form:input type="text" path="quantity" id="quantity" class="input-xlarge"/>
                            <form:button class="btn btn-inverse btn-product-detail" type="submit">Add to cart</form:button>
                            <form:errors path="quantity" class="form-error"></form:errors>
                        </form:form>
                    </div>							
                </div>
                <div class="row">
                    <div class="span9">
                        <ul class="nav nav-tabs" id="myTab">
                            <li class="active"><a href="#home">Description</a></li>
                            <li class=""><a href="#profile">Seller Details</a></li>
                        </ul>							 
                        <div class="tab-content">
                            <div class="tab-pane active" id="home">${cartItemForm.product.description}</div>
                            <div class="tab-pane" id="profile">
                                ${cartItemForm.product.seller.description}
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