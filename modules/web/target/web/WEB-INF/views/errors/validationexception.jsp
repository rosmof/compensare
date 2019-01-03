<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="errors" type="java.util.List<org.springframework.validation.Errors>"--%>
<%--@elvariable id="requestUri" type="java.lang.String"--%>
<%--@elvariable id="sessionId" type="java.lang.String"--%>
<%--@elvariable id="exception" type="java.lang.String"--%>
<%--@elvariable id="exception" type="java.lang.String"--%>
<%--@elvariable id="exceptionStack" type="java.lang.String[]"--%>
<%--@elvariable id="statusValue" type="java.lang.String"--%>
<%--@elvariable id="statusReason" type="java.lang.String"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Validation Exception</title>
</head>
<body>
<h1>Validation Exception Page</h1>
<p>
    Errors:
</p>
<div>
    <c:if test="${errors==null}">
        <div><i>There are no errors;</i></div>
    </c:if>
    <c:if test="${errors.size()>0}">
        <c:forEach items="${errors}" var="error">
            <li>${error}</li>
        </c:forEach>
    </c:if>
</div>

<p>
<div>
    <li>
        Requested URI:
        <c:choose>
            <c:when test="${requestUri==null}">
                <i>no uri provided</i>
            </c:when>
            <c:otherwise>
                <b>${requestUri}</b>
            </c:otherwise>
        </c:choose>
    </li>
    <li>
        Session Id:
        <c:choose>
            <c:when test="${sessionId==null}">
                <i>no session id provided</i>
            </c:when>
            <c:otherwise>
                <b>${sessionId}</b>
            </c:otherwise>
        </c:choose>
    </li>
    <li>
        Exception:
        <c:choose>
            <c:when test="${exception==null}">
                <i>no exception provided</i>
            </c:when>
            <c:otherwise>
                <b>${exception}</b>
            </c:otherwise>
        </c:choose>
    </li>
    <li>
        Exception stack:
        <c:choose>
            <c:when test="${exceptionStack==null}">
                <i>no exception stack associated</i>
            </c:when>
            <c:otherwise>
                <b>
                    <c:forEach items="${exceptionStack}" var="ex">
                        ${ex}</br>
                    </c:forEach>
                </b>
            </c:otherwise>
        </c:choose>
    </li>
    <li>
        Status value:
        <c:choose>
            <c:when test="${statusValue==null}">
                <i>no status value associated</i>
            </c:when>
            <c:otherwise>
                <b>${statusValue}</b>
            </c:otherwise>
        </c:choose>
    </li>
    <li>
        Status reason:
        <c:choose>
            <c:when test="${statusReason==null}">
                <i>no status reason associated</i>
            </c:when>
            <c:otherwise>
                <b>${statusReason}</b>
            </c:otherwise>
        </c:choose>
    </li>
</div>
</p>

</body>
</html>
