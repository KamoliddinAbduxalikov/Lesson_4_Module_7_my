<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="pdp.uz.lesson_4_module_7.model.Book" %>
<%@ page import="static pdp.uz.lesson_4_module_7.list.BookList.BOOKS_LIST" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Book Registration</h1>
<form action="books" method="post">
    <label>
        Book Name: <input type="text" name="name" placeholder="enter book name..." required>
    </label>
    <label>
        Author: <input type="text" name="author_name" placeholder="enter author name..." required>
    </label>
    <label>
        Published Date: <input type="date" name="published_year" required>
    </label>
    <label>
        Email : <input type="text" name="email" required>
    </label>
    <label>
        ICN Number: <p>(ICN number is a number of book that must be unique)</p> <input type="text" name="icn_number" required>
    </label>
    <button type="submit">Register Book</button>
</form>
<%
    if (request.getMethod().equalsIgnoreCase("POST")) {
        Long id = Long.valueOf(request.getParameter("id"));
        String bookName = request.getParameter("name");
        String author = request.getParameter("author_name");
        String publishedYear = request.getParameter("published_year");
        String email = request.getParameter("email");
        String icn = request.getParameter("icn_number");
        BOOKS_LIST.add(new Book(id, bookName, author, publishedYear,email, icn));
    }
%>
<h1>
    Books List
</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Book Name</th>
        <th>Author</th>
        <th>Published Year</th>
        <th>Email</th>
        <th>ICN Number</th>
    </tr>
    <tbody>
    <c:forEach var="book" varStatus="stat" items="${BOOKS}">
        <tr>
            <td>${book.id}</td>
            <td>${book.bookName}</td>
            <td>${book.author}</td>
            <td>${book.publishedYear}</td>
            <td>${book.email}</td>
            <td>${book.icn}</td>
            <td><a href="/books/update?id=${book.id}">✏️</a></td>
            <td><a href="/books/delete?id=${book.id}">🗑️</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>