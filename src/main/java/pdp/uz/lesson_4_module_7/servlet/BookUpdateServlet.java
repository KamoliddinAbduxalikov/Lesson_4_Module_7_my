package pdp.uz.lesson_4_module_7.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pdp.uz.lesson_4_module_7.datasource.DatabaseManager;
import pdp.uz.lesson_4_module_7.model.Book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("id"));
        try (Connection connection = DatabaseManager.connect()) {
            var statement = connection.prepareStatement("select * from book where id = ?");
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getLong("id"))
                        .bookName(resultSet.getString("name"))
                        .author(resultSet.getString("author_name"))
                        .publishedYear(resultSet.getString("published_year"))
                        .email(resultSet.getString("email"))
                        .icn(resultSet.getString("icn_number"))
                        .build();
                req.setAttribute("book", book);
                req.getRequestDispatcher("book-edit.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String bookName = req.getParameter("name");
        String author = req.getParameter("author_name");
        String publishedYear = req.getParameter("published_year");
        String email = req.getParameter("email");
        String icn = req.getParameter("icn_number");

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection
                    .prepareStatement("update book set name = ? , author_name = ? , published_year = ?, email = ? , icn_number = ?  where id = ?");
            statement.setString(1, bookName);
            statement.setString(2, author);
            statement.setString(3, publishedYear);
            statement.setString(4,email);
            statement.setString(5, icn);
            statement.setLong(6, id);
            statement.execute();
            resp.sendRedirect("/books");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
