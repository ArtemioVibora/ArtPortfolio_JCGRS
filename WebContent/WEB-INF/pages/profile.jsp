<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
</head>
<body style="background-color: ${cookie.theme.value == 'dark' ? '#333' : '#FFF'}; color: ${cookie.theme.value == 'dark' ? '#FFF' : '#000'};">
    <h1>User Profile</h1>
    
    <c:if test="${param.updated eq 'true'}">
        <p style="color: green;">Profile settings updated successfully!</p>
    </c:if>

    <p>User ID: <strong>${sessionScope.user.userId}</strong></p>
    <p>Username: <strong>${sessionScope.user.username}</strong></p>
    <p>Email: <strong>${sessionScope.user.email}</strong></p>
    <p>Theme Preference (Cookie): <strong>${cookie.theme.value != null ? cookie.theme.value : 'light'}</strong></p>
    
    <hr>
    
    <div>
        <a href="${pageContext.request.contextPath}/settings"><button type="button">Edit Settings</button></a>
        <a href="${pageContext.request.contextPath}/home"><button type="button">Back to Home</button></a>
    </div>
</body>
</html>
