<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<spring:url value="/themes/images" var="themeImg" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/messages.jsp"></jsp:include>
        <section class="header_text sub_title">
            <h2><span>Login / Regsiter</span></h2>
        </section>			
        <section class="main-content">				
            <div class="row">
                <div class="span5">					
                    <h4 class="title"><span class="text"><strong>Login</strong> Form</span></h4>
                    <form:form action="${basedURL}user/login" method="post" commandName="loginForm">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label">Username</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your username" path="userCode" id="userCode" class="input-xlarge"/>
                                </div>
                                <form:errors path="userCode" class="form-error"></form:errors>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Password</label>
                                <div class="controls">
                                    <form:input type="password" placeholder="Enter your password" id="password" path="password" class="input-xlarge"/>
                                </div>
                                <form:errors path="password" class="form-error"></form:errors>
                            </div>
                            <div class="control-group">
                                <input tabindex="3" class="btn btn-inverse large" type="submit" value="Sign into your account"/>
                            </div>
                        </fieldset>
                    </form:form>				
                </div>
                <div class="span7">					
                    <h4 class="title"><span class="text"><strong>Register</strong> Form</span></h4>
                    <form:form action="${basedURL}user/register" method="post" class="form-stacked" commandName="userForm">
                        <fieldset>
                            <div class="control-group radiobtn">

                                <div class="controls">
                                    <form:radiobutton path="userType" class="input-xlarge" value="2" />
                                </div>
                                <label class="control-label">I am a Seller</label>

                                <div class="controls">
                                    <form:radiobutton path="userType" class="input-xlarge" value="1"/>
                                </div>
                                <label class="control-label">I am a Buyer</label>
                                <form:errors path="userType" class="form-error"></form:errors>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Username</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your username" path="userCode" class="input-xlarge"/>
                                    <form:errors path="userCode" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> First Name:</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your First name" path="firstName" class="input-xlarge"/>
                                    <form:errors path="firstName" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Last Name:</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your last name" path="lastName" class="input-xlarge" />
                                    <form:errors path="lastName" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"> <span class="required">*</span> Email address:</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your email" path="email" class="input-xlarge"/>
                                    <form:errors path="email" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Phone:</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your phone" path="phone" class="input-xlarge"/>
                                    <form:errors path="phone" class="form-error"></form:errors>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Country</label>
                                <div class="controls">
                               
                                    <form:select path="country" class="input-xlarge">
									  <form:option value="" label="--- Select ---" />
									  <form:options items="${countryList}" />
								     </form:select>
                                     <form:errors path="country" class="form-error"></form:errors>
                                </div>
                            </div>
                           <div class="control-group seller-field ${userForm.userType eq '2' ? 'show' : 'hide'}">
                                <label class="control-label"><span class="required">*</span> Shop Name:</label>
                                <div class="controls">
                                    <form:input type="text" placeholder="Enter your Shop Name" path="shopName" class="input-xlarge"/>
                                    <form:errors path="shopName" class="form-error"></form:errors>
                                </div> 
                                
                            </div>
                            <div class="control-group seller-field ${userForm.userType eq '2' ? 'show' : 'hide'}">
                                <label class="control-label"><span class="required">*</span> Description:</label>
                                <div class="controls">
                                    <form:textarea type="text" placeholder="Enter your Description" path="description" class="input-xlarge"/>
                                    <form:errors path="description" class="form-error"></form:errors>
                                </div>
                                
                            </div>
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Password:</label>
                                <div class="controls">
                                    <form:input type="password" placeholder="Enter your password" path="password" class="input-xlarge"/>
                                    <form:errors path="password" class="form-error"></form:errors>
                                </div>
                            </div>	
                            <div class="control-group">
                                <label class="control-label"><span class="required">*</span> Confirm Password:</label>
                                <div class="controls">
                                    <form:input type="password" placeholder="Enter your password" path="confirmPassword" class="input-xlarge"/>
                                    <form:errors path="confirmPassword" class="form-error"></form:errors>
                                </div>
                            </div>
                            <hr>
                            <div class="actions"><input tabindex="9" class="btn btn-inverse large" type="submit" value="Create your account"></div>
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
        
        $('input[type=radio][name=userType]').change(function() {
            if (this.value == 1) {
                $('.seller-field').hide();
            } else if (this.value == 2) {
                $('.seller-field').show();
            }
        });
    });
</script>		
</body>
</html>
