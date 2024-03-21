<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
</head>
<body>
<nav>
    <h1>
        Welcome to Library
    </h1>
    <p><a href="/books">Book List</a></p>
    <c:if test="${not empty sessionScope.currentUser}">
        <div class="user-info">
            <img src="user-icon.png" alt="User Icon">
            <span>${sessionScope.currentUser.username}</span>
        </div>
    </c:if>
</nav>
<div>
    <h2>Welcome to my website!</h2>
    <p>This is the home page content.</p>
</div>
</body>
</html>
