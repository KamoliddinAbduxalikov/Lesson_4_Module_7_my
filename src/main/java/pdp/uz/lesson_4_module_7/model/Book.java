package pdp.uz.lesson_4_module_7.model;

import jakarta.servlet.http.HttpServlet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends HttpServlet {
    private String bookName;
    private String author;
    private String publishedYear;
    private String icn;
}
