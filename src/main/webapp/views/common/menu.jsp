<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user.userType == 2}">
    <jsp:include page="/views/seller/menu.jsp"></jsp:include>
</c:if>
<c:if test="${sessionScope.user.userType != 2}">
    <jsp:include page="/views/buyer/menu.jsp"></jsp:include>
</c:if>