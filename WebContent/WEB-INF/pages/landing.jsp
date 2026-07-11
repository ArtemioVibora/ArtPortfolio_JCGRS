<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Landing Page</title>
  </head>

  <body>
    <h1>Welcome to ArtPortfolio</h1>
    <p>Please log in or register to manage your portfolio and preferences.</p>

    <div>
      <a href="${pageContext.request.contextPath}/login"
        ><button type="button">Login</button></a
      >
      <a href="${pageContext.request.contextPath}/register"
        ><button type="button">Register</button></a
      >
    </div>
  </body>
</html>
