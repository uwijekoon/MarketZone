<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty successMessage}" >
    <div class="alert alert-success">
        <i class="ion-checkmark-circled"></i>
            <span id="successMessageText"><c:out value="${successMessage}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty warningMessage}">
    <div class="alert alert-warning">
        <i class="ion-alert-circled"></i>
           <span id="warningMessageText"><c:out value="${warningMessage}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty errorMessage}" >
    <div class="alert alert-danger">
        <i class="ion-close-circled"></i>
            <span id="errorMessageText"><c:out value="${errorMessage}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>


<c:if test="${not empty infoMessage}" >
    <div class="alert alert-info">
        <i class="ion-information-circled"></i>
            <span id="infoMessageText"><c:out value="${infoMessage}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty flashSuccess}" >
    <div class="alert alert-success">
        <i class="ion-checkmark-circled"></i>
            <span id="successMessageText"><c:out value="${flashSuccess}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty flashWarning}">
    <div class="alert alert-warning">
        <i class="ion-alert-circled"></i>
            <span id="warningMessageText"><c:out value="${flashWarning}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty flashError}" >
    <div class="alert alert-danger">
        <i class="ion-close-circled"></i>
            <span id="errorMessageText"><c:out value="${flashError}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

<c:if test="${not empty flashInfo}" >
    <div class="alert alert-info">
        <i class="ion-information-circled"></i>
            <span id="infoMessageText"><c:out value="${flashInfo}" /></span></br>
        <i class="ion-close"></i>
    </div>
</c:if>

