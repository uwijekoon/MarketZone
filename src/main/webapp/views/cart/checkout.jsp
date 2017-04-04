<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="basedURL" />
<jsp:include page="/views/common/header.jsp"></jsp:include>
    <div id="wrapper" class="container">
    <jsp:include page="/views/common/menu.jsp"></jsp:include>
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
                                            <form action="${pageContext.request.contextPath}/register" method="post">
                                                <fieldset>
                                                    <label class="radio" for="register">
                                                        <input type="radio" name="account" value="register" id="register" checked="checked">Register Account
                                                    </label>
                                                    <!--<label class="radio" for="guest">
                                                        <input type="radio" name="account" value="guest" id="guest">Guest Checkout
                                                    </label>-->
                                                    <br>
                                                    <button class="btn btn-inverse" data-toggle="collapse" data-parent="#collapse2">Continue</button>
                                                </fieldset>
                                            </form>
                                        </div>
                                        <div class="span6">
                                            <h4>Returning Customer</h4>
                                            <p>I am a returning customer</p>
                                            <form action="${pageContext.request.contextPath}/Login" method="post">
                                                <fieldset>
                                                    <div class="control-group">
                                                        <label class="control-label">Username</label>
                                                        <div class="controls">
                                                            <input type="text" placeholder="Enter your username" id="username" name="username" class="input-xlarge">
                                                        </div>
                                                    </div>
                                                    <div class="control-group">
                                                        <label class="control-label">Password</label>
                                                        <div class="controls">
                                                            <input type="password" placeholder="Enter your password" id="password" name="password" class="input-xlarge">
                                                        </div>
                                                    </div>
                                                    <button class="btn btn-inverse">Continue</button>
                                                </fieldset>
                                            </form>
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
                                                        <input type="text" placeholder="" class="input-xlarge" name="firstName" value="${firstName}">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Last Name</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="lastName" value="${lastName}">
                                                    </div>
                                                </div>					  
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Email Address</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="email" value="${email}">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">Telephone</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="telephone" value="${telephone}">
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="span6">
                                                <h4>Your Address</h4>

                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Address 1:</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="address1" value="${address1}">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label">Address 2:</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="address2" value="${address2}">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> City:</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="city" value="${city}">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Postal Code:</label>
                                                    <div class="controls">
                                                        <input type="text" placeholder="" class="input-xlarge" name="postalCode" value="${postalCode}">
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label"><span class="required">*</span> Country:</label>
                                                    <div class="controls">
                                                        <select class="input-xlarge" name="country">
                                                            <option value="1">Afghanistan</option>
                                                            <option value="2">Albania</option>
                                                            <option value="3">Algeria</option>
                                                            <option value="4">American Samoa</option>
                                                            <option value="5">Andorra</option>
                                                            <option value="6">Angola</option>
                                                        </select>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">Confirm Order</a>
                                </div>
                                <div id="collapseThree" class="accordion-body collapse">
                                    <div class="accordion-inner">
                                        <div class="row-fluid">
                                            <div class="control-group">
                                                <label for="textarea" class="control-label">Comments</label>
                                                <div class="controls">
                                                    <textarea rows="3" id="textarea" class="span12" name="comments"></textarea>
                                                </div>
                                            </div>									
                                            <button class="btn btn-inverse pull-right">Confirm order</button>
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