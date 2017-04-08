<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<spring:url value="/themes/images" var="themeImg" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
        <section  class="homepage-slider" id="home-slider">
            <div class="flexslider">
                <ul class="slides">
                    <li>
                        <img src="${themeImg}/carousel/banner-1.jpg" alt="" />
                </li>
                <li>
                    <img src="${themeImg}/carousel/banner-2.jpg" alt="" />
                    <div class="intro">
                        <h1>Mid season sale</h1>
                        <p><span>Up to 50% Off</span></p>
                        <p><span>On selected items online and in stores</span></p>
                    </div>
                </li>
            </ul>
        </div>			
    </section>
    <section class="header_text">
    </section>
    <section class="main-content">
        <div class="row">
            <div class="span12">													
                <div class="row">
                    <div class="span12">
                        <h4 class="title">
                            <span class="pull-left"><span class="text"><span class="line"><strong>Products</strong> List</span></span></span>
                            <span class="pull-right">
                                <a class="left button" href="#myCarousel-2" data-slide="prev"></a><a class="right button" href="#myCarousel-2" data-slide="next"></a>
                            </span>
                        </h4>
                        <div>
                        	<h5>Search Products</h5>
                            <form:form action="${basedURL}product/search" method="get" commandName="searchForm">
		                        <fieldset>
		                            <div class="control-group product-search">
		                                <h5><label class="search-label control-label">Name</label></h5>
		                                <div class="controls">
		                                    <form:input type="text" placeholder="Enter Product Name" path="productName" id="productName" class="input-xlarge"/>
		                                    <form:errors path="productName" class="form-error"></form:errors>
		                                </div>
		                            </div>
		                            <div class="search-btn control-group ">
		                                <input tabindex="3" class="btn btn-inverse large" type="submit" value="Search"/>
		                            </div>
		                        </fieldset>
		                    </form:form>
		                    </div>
                        
                        <div id="myCarousel-2" class="myCarousel carousel slide">
                            <div class="carousel-inner">

                                <c:forEach var="product" items="${availableProductList}" varStatus="index">

                                    <c:if test="${index.index % 8 == 0}">

                                        <div class="item <c:if test='${index.index == 0}'>active</c:if>">
                                                <ul class="thumbnails">
                                            </c:if>
                                            <li class="span3">
                                                <div class="product-box">
                                                    <span class="sale_tag"></span>
                                                    <div><a href="${pageContext.request.contextPath}/product/getProduct?productId=${product.id}">
                                                            <c:choose>
                                                                <c:when test="${not empty product.image}">
                                                                    <img width="100" height="100" src="${pageContext.request.contextPath}/image/${product.seller.id}/${product.image}" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img width="100" height="100" src="${pageContext.request.contextPath}/themes/images/no_image.png" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </a></div>
                                                    <a href="${pageContext.request.contextPath}/product/getProduct?productId=${product.id}" class="title">${product.name}</a><br/>
                                                    <a href="${pageContext.request.contextPath}/product/getProduct?productId=${product.id}" class="category">${product.seller.name}</a>
                                                    <p class="price"><fmt:formatNumber value="${product.unitPrice}" type="currency"/></p>
                                                </div>
                                            </li>
                                            <c:if test="${index.index % 8 == 7}">
                                            </ul>
                                        </div>

                                    </c:if>
                                </c:forEach>
                                
                                <c:if test="${empty availableProductList}">
                                	There is no product that matches the search criteria!
								</c:if>
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
<script src="${pageContext.request.contextPath}/themes/js/jquery.flexslider-min.js"></script>
<script type="text/javascript">
    $(function () {
        $(document).ready(function () {
            $('.flexslider').flexslider({
                animation: "fade",
                slideshowSpeed: 4000,
                animationSpeed: 600,
                controlNav: false,
                directionNav: true,
                controlsContainer: ".flex-container" // the container that holds the flexslider
            });
        });
    });
</script>
</body>
</html>