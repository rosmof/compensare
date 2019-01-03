<%@ page import="java.util.logging.Logger" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome to Spittr</h1>
<a href="<c:url value="/home" />">Spittles</a> |
<a href="<c:url value="/another" />">Register</a>

<div>${splitterList.size()}</div>
<%
    Logger logger = Logger.getLogger("webpage");
    logger.warning("this is ok");
%>
<c:forEach items="${splitterList}" var="fantasy">
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <a href="<c:url value="/home/${fantasy.id}"/>"><c:out value="${fantasy.message}"/></a>
        </div>
        <div>
            <span class="spittleTime">
                <c:out value="${fantasy.time}"/>
            </span>
            <span class="spittleLocation">
                (<c:out value="${fantasy.latitude}"/>,<c:out value="${fantasy.longitude}"/>)
            </span>
        </div>
    </li>
</c:forEach>
</body>
</html>