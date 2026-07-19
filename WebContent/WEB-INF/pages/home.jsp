<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>

    <div class="nav-header win95-box">
        <h2>HOME FEED</h2>
        <div style="margin-left: auto;">
            <a href="${pageContext.request.contextPath}/profile/user-activity"><button class="win95-button" type="button">User Activity</button></a>
            <a href="${pageContext.request.contextPath}/profile"><button class="win95-button" type="button">Profile</button></a>
            <a href="${pageContext.request.contextPath}/settings"><button class="win95-button" type="button">Settings</button></a>
            <a href="${pageContext.request.contextPath}/logout"><button class="win95-button" type="button">Logout</button></a>
        </div>
    </div>

    <div class="feed-container">
        
        <div class="win95-box">
            <div class="win95-header">
                <span>System_Status.exe</span>
            </div>
            <div style="padding: 15px;">
                <p>Welcome, <strong>${sessionScope.user.username}</strong>!</p>
            </div>
        </div>

        <div class="win95-box artwork-card">
            <div class="win95-header">
                <span>Artwork_Viewer.exe</span>
                <span>X</span>
            </div>
            
            <img src="https://thumbs.dreamstime.com/b/cute-girl-long-pink-hair-hair-coloring-beautiful-woman-pink-background-white-dress-colored-hair-perfect-cute-girl-195568566.jpg" alt="Placeholder Art">
            
            <div class="artwork-info">
                <div class="artist-name">Artist: @SambaTheGreat</div>
                <div class="artwork-title">Title: "Retro Dreams"</div>
                <p>Posted: Oct 24, 1995</p>
            </div>
        </div>

    </div>

</body>
</html>