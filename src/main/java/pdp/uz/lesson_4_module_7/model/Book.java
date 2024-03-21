package pdp.uz.lesson_4_module_7.model;

import jakarta.servlet.http.HttpServlet;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends HttpServlet {
    private Long id;
    private String bookName;
    private String author;
    private String publishedYear;
    private String email;
    private String icn;
}
