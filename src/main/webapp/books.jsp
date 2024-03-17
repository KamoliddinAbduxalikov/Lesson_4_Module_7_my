<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pdp.uz.lesson_4_module_7.model.Book" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="pdp.uz.lesson_4_module_7.datasource.DatabaseManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
    List<Book> BOOKS = new ArrayList<>();
%>
<html>
<head>
    <title>Books</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Book Registration</h1>
<form action="book" method="post">
    <label>
        Book Name: <input type="text" name="bookName" placeholder="enter book name...">
    </label>
    <label>
        Author: <input type="text" name="author" placeholder="enter author name...">
    </label>
    <label>
        Published Date: <input type="date" name="publishedYear">
    </label>
    <label>
        ICN Number: <input type="text" name="icn">
    </label>
    <button type="submit">Register Book</button>
</form>
<%
    if (request.getMethod().equalsIgnoreCase("POST")) {
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String publishedYear = request.getParameter("publishedYear");
        String icn = request.getParameter("icn");
        BOOKS.add(new Book(bookName, author, publishedYear, icn));
    }
%>
<h1>
    Books List
</h1>
<table>
    <tr>
        <th>Book Name</th>
        <th>Author</th>
        <th>Published Year</th>
        <th>ICN Number</th>
    </tr>

    <% // Java code block for retrieving books from the database %>
    <%
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM book");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String bookName = resultSet.getString("bookName");
                String author = resultSet.getString("author");
                String publishedYear = resultSet.getString("publishedYear");
                String icn = resultSet.getString("icn");
    %>

    <tr>
        <td><%= bookName %>
        </td>
        <td><%= author %>
        </td>
        <td><%= publishedYear %>
        </td>
        <td><%= icn %>
        </td>
    </tr>

    <% // End of while loop %>
    <% } %>

    <% // Exception handling %>
    <% } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    %>
</table>
</body>
</html>