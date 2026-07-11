<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
</head>
<body style="background-color: ${cookie.theme.value == 'dark' ? '#333' : '#FFF'}; color: ${cookie.theme.value == 'dark' ? '#FFF' : '#000'};">
    <h1>Settings</h1>
    
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/settings" method="post">
        <div>
            <label for="username">Change Username:</label>
            <input type="text" id="username" name="username" placeholder="${sessionScope.user.username}">
        </div>
        <br>
        <div>
            <label for="email">Change Email:</label>
            <input type="email" id="email" name="email" placeholder="${sessionScope.user.email}">
        </div>
        <br>
        <div>
            <label for="password">Change Password:</label>
            <input type="password" id="password" name="password" placeholder="Leave blank to keep current">
        </div>
        <br>
        <div>
            <label>Theme Preference:</label>
            <br>
            <input type="radio" id="light" name="theme" value="light" ${cookie.theme.value != 'dark' ? 'checked' : ''}>
            <label for="light">Light</label>
            <br>
            <input type="radio" id="dark" name="theme" value="dark" ${cookie.theme.value == 'dark' ? 'checked' : ''}>
            <label for="dark">Dark</label>
        </div>
        <br>
        <div>
            <input type="submit" value="Save Settings">
            <a href="${pageContext.request.contextPath}/settings?action=delete"><button type="button">Delete Account</button></a>
            <a href="${pageContext.request.contextPath}/profile"><button type="button">Cancel</button></a>
        </div>
    </form>
</body>
</html>
