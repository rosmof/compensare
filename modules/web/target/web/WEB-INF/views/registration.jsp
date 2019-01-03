<%--
  Created by IntelliJ IDEA.
  User: AlexandruG
  Date: 25/12/2018
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<%--@elvariable id="registration" type="ro.rosmof.model.Registration"--%>
<form:form modelAttribute="registration" method="post" enctype="multipart/form-data">
    First Name: <input type="text" name="FirstName"/><br/>
    Last Name: <input type="text" name="LastName"/><br/>
    Username: <input type="text" name="Username"/><br/>
    Password: <input type="password" name="Password"/><br/>
    <label>Document:</label>
    <input type="file" path="file" name="file" accept="application/pdf"/>
    <input type="submit" value="Register"/>
</form:form>
</body>
</html>
