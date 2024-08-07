package library.web.ui;

import library.model.Book;
import library.model.Library;
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
public class LibraryController {

    private final LibraryService libraryService;
    private final BookService bookService;

    @GetMapping("/libraries")
    String getLibraries(
            Model model,
            @RequestParam(value = "bookId", required = false) Integer bookId) {
        //log.info("about to display libraries list");
        //List<Library> libraries = libraryService.getAllLibraries();
        //model.addAttribute("libraries", libraries);

        log.info("about to display libraries list for book {}", bookId);
        if (bookId != null) {
            Book book = bookService.getBookById(bookId);
            List<Library> libraries = libraryService.getLibrariesByBook(book);
            model.addAttribute("libraries", libraries);
            model.addAttribute("title", "Libraries for book: '" + book.getTitle() + "'");
        } else {
            List<Library> libraries = libraryService.getAllLibraries();
            model.addAttribute("libraries", libraries);
            model.addAttribute("title", "Libraries");
        }

        return "librariesView";
    }
}
