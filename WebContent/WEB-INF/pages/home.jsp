<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body style="background-color: ${cookie.theme.value == 'dark' ? '#333' : '#FFF'}; color: ${cookie.theme.value == 'dark' ? '#FFF' : '#000'};">
    <h1>Home Dashboard</h1>
    <p>Welcome, <strong>${sessionScope.user.username}</strong>!</p>
    <p>Current Theme Preference (Cookie): <strong>${cookie.theme.value != null ? cookie.theme.value : 'light'}</strong></p>
    
    <hr>
    
    <div>
        <a href="${pageContext.request.contextPath}/profile"><button type="button">Go to Profile</button></a>
        <a href="${pageContext.request.contextPath}/settings"><button type="button">Go to Settings</button></a>
        <a href="${pageContext.request.contextPath}/logout"><button type="button">Logout</button></a>
    </div>
</body>
</html>
