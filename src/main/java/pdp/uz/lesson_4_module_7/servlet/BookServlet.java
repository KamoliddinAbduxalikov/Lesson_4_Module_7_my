package pdp.uz.lesson_4_module_7.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("books.jsp").forward(req,resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("books.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        String publishedYear = req.getParameter("publishedYear");
        String icn = req.getParameter("icn");

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement("insert into book(bookName, author, publishedYear, icn) values (?, ?, ?, ?)");
            statement.setString(1, bookName);
            statement.setString(2, author);
            statement.setString(3, publishedYear);
            statement.setString(4, icn);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/book");
    }
}