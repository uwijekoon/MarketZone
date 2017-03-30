<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty successMessage}" >
    <div class="alert alert-success">
        <i class="ion-checkmark-circled"></i>
        <c:forEach var="success" items="${successMessage}">
            <span id="successMessageText"><c:out value="${success}" /></span></br>
        </c:forEach>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty warningMessage}">
    <div class="alert alert-warning">
        <i class="ion-alert-circled"></i>
        <c:forEach var="warning" items="${warningMessage}">
            <span id="warningMessageText"><c:out value="${warning}" /></span></br>
        </c:forEach>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty errorMessage}" >
    <div class="alert alert-danger">
        <i class="ion-close-circled"></i>
        <c:forEach var="error" items="${errorMessage}">
            <span id="errorMessageText"><c:out value="${error}" /></span></br>
        </c:forEach>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty infoMessage}" >
    <div class="alert alert-info">
        <i class="ion-information-circled"></i>
        <c:forEach var="info" items="${infoMessage}">
            <span id="infoMessageText"><c:out value="${info}" /></span></br>
        </c:forEach>
        <i class="ion-close"></i>
    </div>
</c:if>
