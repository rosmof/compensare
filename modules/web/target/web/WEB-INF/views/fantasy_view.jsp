<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fantasy View</title>
</head>
<body>
<h1>Fantasy view</h1>

<c:forEach items="${fantasyList}" var="fantasy">
    <li id="spittle_<c:out value="fantasy.id"/>">
        <div class="spittleMessage">
            <a href="<c:url value="/fantasy/${fantasy.id}"/>"><c:out value="${fantasy.name}"/></a>
        </div>
        <div>
            <span class="spittleTime">
                <c:out value="${fantasy.start}"/>
            </span>
            <span class="spittleLocation">
                (<c:out value="${fantasy.counter}"/>)
            </span>
        </div>
    </li>
</c:forEach>

</body>
</html>
