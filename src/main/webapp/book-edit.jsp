<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Edit</title>
</head>
<body>
<form action="/books/update" method="post">
    <input type="hidden" name="id" value="${book.id}">
    <label>
        Book : <input type="text" name="name" value="${book.bookName}">
    </label>
    <br>
    <br>
    <label>
        Author : <input type="text" name="author_name" value="${book.author}">
    </label>
    <br>
    <br>
    <label>
        Published Year : <input type="text" name="published_year" value="${book.publishedYear}">
    </label>
    <br>
    <br>
    <label>
        Email : <input type="text" name="email" value="${book.email}">
    </label>
    <br>
    <br>
    <label>
        ICN Number : <input type="text" name="icn_number" value="${book.icn}">
    </label>
    <br>
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>
