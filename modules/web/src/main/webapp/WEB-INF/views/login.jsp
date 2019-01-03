<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AlexandruG
  Date: 31/12/2018
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login Page</title></head>

<h3>Login with Username and Password</h3>

<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post"> 1
    <c:if test="${param.error != null}"> 2
        <p>
            Invalid username and password. <br/>
            "${param.error}"
        </p>
    </c:if>
    <c:if test="${param.logout != null}"> 3
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/> 4
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/> 5
    </p>
    <input type="hidden" 6
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn">Log in</button>
</form>
</body>
</html>