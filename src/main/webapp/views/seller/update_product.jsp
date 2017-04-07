<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<spring:url value="/themes/images" var="themeImg" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/messages.jsp"></jsp:include>
        <section class="header_text sub">
            <h2><span>Update Product</span></h2>
        </section>			
        <section class="main-content">				
            <div class="row">
                <div class="span11 offset4">
                    <form:form action="${basedURL}seller/updateProduct" method="post" commandName="productForm" >
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Name</label>
                                <div class="controls">
                                 	<form:hidden path="id"/>
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
                            <div class="control-group">
                                <input tabindex="3" class="btn btn-inverse large" type="submit" value="Update Product">
                            </div>
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </section>			
    <jsp:include page="/views/common/footer.jsp"></jsp:include>
    </div>
    <script src="${basedURL}themes/js/common.js"></script>		
</body>
</html>
