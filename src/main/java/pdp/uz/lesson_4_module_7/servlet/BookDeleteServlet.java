package pdp.uz.lesson_4_module_7.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pdp.uz.lesson_4_module_7.datasource.DatabaseManager;

import java.io.IOException;
import java.sql.Connection;

public class BookDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("id"));
        try (Connection connection = DatabaseManager.connect()) {
            var statement = connection.prepareStatement("delete from book where id = ?");
            statement.setLong(1,bookId);
            statement.execute();
            resp.sendRedirect("/books");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
