package pdp.uz.lesson_4_module_7.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pdp.uz.lesson_4_module_7.datasource.DatabaseManager;
import pdp.uz.lesson_4_module_7.model.Book;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connect = DatabaseManager.connect()) {
            ResultSet resultSet = connect.prepareStatement("select * from book").executeQuery();
            List<Book> BOOKS = new ArrayList<>();
            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getLong("id"))
                        .bookName(resultSet.getString("name"))
                        .author(resultSet.getString("author_name"))
                        .publishedYear(resultSet.getString("published_year"))
                        .email(resultSet.getString("email"))
                        .icn(resultSet.getString("icn_number"))
                        .build();
                BOOKS.add(book);
            }
            req.setAttribute("BOOKS", BOOKS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("books.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("name");
        String author = req.getParameter("author_name");
        String publishedYear = req.getParameter("published_year");
        String email = req.getParameter("email");
        String icn = req.getParameter("icn_number");

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO book (name, author_name, published_year, icn_number, email) VALUES (?, ?, ?, ?,?)");
            statement.setString(1, bookName);
            statement.setString(2, author);
            statement.setString(3, publishedYear);
            statement.setString(4, icn);
            statement.setString(5, email);
            statement.execute(); // Use executeUpdate for INSERT operation
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Cookie cookie = new Cookie("author", author);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30);
        cookie.setPath("/");

        resp.addCookie(cookie);

        // Redirect to the correct URL
        resp.sendRedirect(req.getContextPath() + "/books"); // Assuming "/books" is the correct URL
    }
}