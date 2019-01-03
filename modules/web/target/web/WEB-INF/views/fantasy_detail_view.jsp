<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Fantasy Detail View</title>
</head>
<body>

<h1>Fantasy Detail View</h1>
<div>
    <li>
        Name:<c:out value="${item.name}"/>
    </li>
    <li>
        Counter: <c:out value="${item.counter}"/>
    </li>
    <li>
        Id: <c:out value="${item.id}"/>
    </li>
    <li>
        Start: <c:out value="${item.start}"/>
    </li>
</div>

</body>
</html>
