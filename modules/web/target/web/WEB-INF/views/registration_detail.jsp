<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="registration" type="ro.rosmof.model.Registration"--%>
<html>
<head>
    <title>Registration Detail</title>
</head>
<body>
<h1>this is registration detail</h1>
You registered with the following credentials: <br/>
Id:${registration.id} <br/>
Registration Date:${registration.registrationDate} <br/>
First Name:${registration.firstName} <br/>
Last Name:${registration.lastName} <br/>
Username:${registration.username} <br/>
Password:${registration.password} <br/>

<div>
    Click <a href="<c:url value="/fantasy" />">for fantasy</a>
</div>

</body>
</html>
