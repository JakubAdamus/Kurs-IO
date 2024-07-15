package library.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import library.model.Book;
import library.model.Library;
import library.service.BookService;
import library.service.LibraryService;
import library.web.rest.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class BookRest {

    private final LibraryService libraryService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

//    private final LibraryValidator validator;
//
//    @InitBinder
//    void initBinder(WebDataBinder binder) {binder.addValidators(validator);}

    @GetMapping("/books")
    List<Book> getBooks(@RequestParam(value = "phrase", required = false) String phrase,
                        @RequestHeader(value = "custom-header", required = false) String customHeader) {

        log.info("about to retrieve books list");
        List<Book> books = bookService.getAllBooks();
        log.info("{} books found", books.size());
        return books;
    }

    @GetMapping("/book/{id}")
    ResponseEntity<Book> getBook(@PathVariable("id") int id) {

        log.info("about to retrieve book {}", id);
        Book book = bookService.getBookById(id);
        log.info("book found", book);

        return book != null
                ? ResponseEntity.status(200).body(book)
                : ResponseEntity.status(404).build();
    }

    @GetMapping("/library/{id}/books")
    ResponseEntity<List<Book>> getBooksInLibrary(@PathVariable("id") int id) {

        log.info("about to retrieve books in library {}", id);
        Library library = libraryService.getLibraryById(id);

        if (library == null) return ResponseEntity.notFound().build();
        else {
            List<Book> books = bookService.getBooksInLibrary(library);
            log.info("there's {} books in library {}", books.size(), library.getName());
            return ResponseEntity.ok(books);
        }
    }

    @PostMapping("/book")
    ResponseEntity<?> addBook(@Validated @RequestBody BookDto bookDTO, Errors errors, HttpServletRequest request) {

        log.info("about to add book {}", bookDTO);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(Objects.requireNonNull(oe.getCode()), new Object[0], locale))
                    .reduce("Error msg:\n", (s1, s2) -> s1 + s2 + "\n");

            log.error("book has errors: {}", errorMessage);

            return ResponseEntity.badRequest().body(errorMessage);
        }

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setCover(bookDTO.getCover());
        book.setWriter(bookService.getWriterById(bookDTO.getWriterId()));
        book.setRating(bookDTO.getRating());

        book = bookService.addBook(book);
        log.info("new book added: {}");

        return ResponseEntity
                .created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequestUri()
                                .path("/" + book.getId())
                                .build()
                                .toUri())
                .body(book);
    }
}
