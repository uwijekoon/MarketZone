<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/messages.jsp"></jsp:include>
        <section class="header_text sub">
            <h2><span>Check Out</span></h2>
        </section>	
        <section class="main-content">
            <div class="row">
                <div class="span12">
                    <div class="accordion" id="accordion2">
                    <c:if test="${sessionScope.user == null}">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">Checkout Options</a>
                            </div>
                            <div id="collapseOne" class="accordion-body in collapse">
                                <div class="accordion-inner">

                                    <div class="row-fluid">
                                        <div class="span6">
                                            <h4>New Customer</h4>
                                            <p>By creating an account you will be able to shop faster, be up to date on an order's status, and keep track of the orders you have previously made.</p>
                                            <form:form action="${basedURL}/user/register" method="get">
                                                <fieldset>
                                                    <label class="radio" for="register">
                                                        <input type="radio" name="account" value="register" id="register" checked="checked">Register Account
                                                    </label>
                                                    <!--<label class="radio" for="guest">
                                                        <input type="radio" name="account" value="guest" id="guest">Guest Checkout
                                                    </label>-->
                                                    <br>
                                                    <input tabindex="3" class="btn btn-inverse large" type="submit" value="Continue"/>
                                                </fieldset>
                                            </form:form>
                                        </div>
                                        <div class="span6">
                                            <h4>Returning Customer</h4>
                                            <p>I am a returning customer</p>
                                           <form:form action="${basedURL}user/login" method="post" commandName="loginForm">
                                           <input type="hidden" name="redirectPage" value="cart/checkout"/>
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
						                                <input tabindex="3" class="btn btn-inverse large" type="submit" value="Continue"/>
						                            </div>
						                        </fieldset>
						                    </form:form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                    <form:form action="${basedURL}cart/checkout" method="post"  commandName="orderForm">  
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">Account &amp; Billing Details</a>
                                </div>
                                <div id="collapseTwo" class="accordion-body in collapse">
                                    <div class="accordion-inner">
                                        <div class="row-fluid">
                                            <div class="span6">
                                                <h4>Your Personal Details</h4>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> First Name</label>
                                                    <div class="controls">
                                                        <form:input placeholder="First Name" class="input-xlarge" path="firstName" />
                                                        <form:errors path="firstName" class="form-error"></form:errors>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Last Name</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="Last Name" class="input-xlarge" path="lastName"/>
                                                        <form:errors path="lastName" class="form-error"></form:errors>
                                                    </div>
                                                </div>					  
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Email Address</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="Email" class="input-xlarge" path="email"/>
                                                        <form:errors path="email" class="form-error"></form:errors>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">Telephone</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="Telephone" class="input-xlarge" path="telephone"/>
                                                        <form:errors path="telephone" class="form-error"></form:errors>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="span6">
                                                <h4>Your Address</h4>

                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Address 1:</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="Address 1" class="input-xlarge" path="address1"/>
                                                        <form:errors path="address1" class="form-error"></form:errors>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">Address 2:</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="Address 2" class="input-xlarge" path="address2"/>
                                                        <form:errors path="address2" class="form-error"></form:errors>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> City:</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="City" class="input-xlarge" path="city" />
                                                        <form:errors path="city" class="form-error"></form:errors>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Postal Code:</label>
                                                    <div class="controls">
                                                        <form:input type="text" placeholder="Postal Code" class="input-xlarge" path="postalCode" />
                                                        <form:errors path="postalCode" class="form-error"></form:errors>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Country:</label>
                                                    <div class="controls">
                                                        <form:select path="country" class="input-xlarge">
														  <form:option value="" label="--- Select ---" />
														  <form:options items="${countryList}" />
													     </form:select>
					                                     <form:errors path="country" class="form-error"></form:errors>
                                                    </div>
                                                </div>

                                            </div>
                                            
                                             <div class="row-fluid">
                                            <div class="control-group">
                                                <label for="textarea" class="control-label">Comments</label>
                                                <div class="controls">
                                                    <form:textarea rows="3" placeholder="Comments" path="comments" class="span12"/>
                                                </div>
                                            </div>									
                                            <button class="btn btn-inverse pull-right">Confirm order</button>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </form:form>
                    </c:if>
                </div>				
            </div>
        </div>
    </section>			
    <jsp:include page="/views/common/footer.jsp"></jsp:include>
    </div>
    <script src="${pageContext.request.contextPath}/themes/js/common.js"></script>
</body>
</html>