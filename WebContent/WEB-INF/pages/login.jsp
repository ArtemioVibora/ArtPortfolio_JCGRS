<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    
    <c:if test="${param.registered eq 'true'}">
        <p style="color: green;">Registration successful! Please login below.</p>
    </c:if>
    
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <label for="usernameOrEmail">Username or Email:</label>
            <input type="text" id="usernameOrEmail" name="usernameOrEmail" required>
        </div>
        <br>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <br>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>
    <br>
    <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a>.</p>
</body>
</html>
