package library.web.ui;

import library.model.Book;
import library.model.Library;
import library.model.Writer;
import library.service.BookService;
import library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final LibraryService libraryService;
    private final BookService bookService;

    @GetMapping("/books")
    String getBooks(
            Model model,
            @RequestParam(value = "libraryId", required = false) Integer libraryId,
            @RequestParam(value = "writerId", required = false) Integer writerId) {
        log.info("about to display books list in library {}", libraryId);
        if (libraryId != null) {
            Library library = libraryService.getLibraryById(libraryId);
            List<Book> books = bookService.getBooksInLibrary(library);
            model.addAttribute("books", books);
            model.addAttribute("title", "Books in library: '" + library.getName() + "'");
        } else if (writerId != null) {
            Writer writer = bookService.getWriterById(writerId);
            List<Book> books = bookService.getBooksByWriter(writer);
            model.addAttribute("books", books);
            model.addAttribute("title", "Books by writer: '" + writer.getLastName() + "'");
        }
        else {
            model.addAttribute("books", bookService.getAllBooks());
            model.addAttribute("title", "Books");
        }
        return "booksView";
    }
}
