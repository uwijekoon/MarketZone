<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<spring:url value="/themes/images" var="themeImg" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/messages.jsp"></jsp:include>
        <section class="header_text sub">
            <h2><span>Add New Product</span></h2>
        </section>			
        <section class="main-content">				
            <div class="row">
                <div class="span11 offset4">
                    <form:form action="${basedURL}seller/addProduct" method="post" commandName="productForm" accept-charset="utf-8" enctype="multipart/form-data" >
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Name</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Product Name" path="name" id="name" class="input-xlarge" />
                                    <form:errors path="name" class="form-error"></form:errors>
                                </div>
                            </div>
                            
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Unit Price</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Unit Price" id="unitPrice" path="unitPrice" class="input-xlarge"/>
                                    <form:errors path="unitPrice" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Quantity</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Quantity" id="quantity" path="quantity" class="input-xlarge"/>
                                    <form:errors path="quantity" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Description</label>
                                    <form:textarea type="text" placeholder="Product Description" path="description" class="input-xlarge"/>
                                    <form:errors path="description" class="form-error"></form:errors>
                             </div>
                            <div class="control-group file-upload">
                                <label class="control-label">Product Image</label>
                                <div class="controls">
                                    <input type="file" placeholder="Quantity" id="quantity" name=imageFile class="input-xlarge">
                                    <form:errors path="imageFile" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <input tabindex="3" class="btn btn-inverse large" type="submit" value="Add Product">
                            </div>
                        </fieldset>
                    </form:form>
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
