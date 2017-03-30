<meta charset="utf-8">
<title>MarketZone Shopping</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:url value="/bootstrap/css/bootstrap.min.css" var="bootstrapMainCss" />
<spring:url value="/bootstrap/css/bootstrap-responsive.min.css" var="bootstrapResponseCss" />
<spring:url value="/themes/css/bootstrappage.css" var="bootstrappageCss" />

<spring:url value="/themes/css/main.css" var="mainCss" />
<spring:url value="/themes/css/jquery.fancybox.css" var="fancyBoxCss" />
<spring:url value="/themes/css/flexslider.css" var="sliderCss" />

<spring:url value="/themes/js/jquery-1.7.2.min.js" var="jqueryJs" />
<spring:url value="/bootstrap/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/themes/js/superfish.js" var="superfishJs" />
<spring:url value="/themes/js/jquery.scrolltotop.js" var="scrolltotopJs" />
<spring:url value="/themes/js/jquery.fancybox.js" var="fancyboxJs" />
<spring:url value="/themes/js/bidzone.js" var="bidzoneJs" />
<spring:url value="/themes/js/respond.min.js" var="respondJs" />
<spring:url value="/" var="basedURL" />

<!-- bootstrap -->
<link href="${bootstrapMainCss}" rel="stylesheet">      
<link href="${bootstrapResponseCss}" rel="stylesheet">		
<link href="${bootstrappageCss}" rel="stylesheet"/>

<!-- global styles -->
<link href="${mainCss}" rel="stylesheet"/>
<link href="${fancyBoxCss}" rel="stylesheet"/>
<link href="${sliderCss}" rel="stylesheet"/>

<!-- scripts -->
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>				
<script src="${superfishJs}"></script>	
<script src="${scrolltotopJs}"></script>
<script src="${fancyboxJs}"></script>
<script src="${bidzoneJs}"></script>
<!--[if lt IE 9]>			
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script src="${respondJs}"></script>
<![endif]-->