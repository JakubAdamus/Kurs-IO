package library.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import library.model.Book;
import library.model.Library;
import library.service.BookService;
import library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class LibraryRest {

    private final LibraryService libraryService;
    private final BookService bookService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping("/libraries")
    List<Library> getLibraries(@RequestParam(value = "phrase", required = false) String phrase,
                               @RequestHeader(value = "custom-header", required = false) String customHeader,
                               @CookieValue(value = "custom-cookie", required = false) String customCookie) {

        log.info("about to retrieve libraries list");
        log.info("phrase parameter: {}", phrase);
        log.info("custom header: {}", customHeader);
        log.info("custom cookie: {}", customCookie);

        List<Library> libraries = libraryService.getAllLibraries();
        log.info("{} libraries found", libraries.size());

        return libraries;
    }

    @GetMapping("/libraries/{id}")
    ResponseEntity<Library> getLibrary(@PathVariable("id") int id) {

        log.info("about to retrieve library {}", id);

        Library library = libraryService.getLibraryById(id);
        log.info("library found", library);

        return (library != null)
                ? ResponseEntity.status(200).body(library)
                : ResponseEntity.status(404).build();
    }

    @GetMapping("/books/{id}/libraries")
    ResponseEntity<List<Library>> getLibrariesByBook(@PathVariable("id") int id) {

        log.info("about to retrieve libraries for book {}", id);
        Book book = bookService.getBookById(id);

        if (book == null) return ResponseEntity.notFound().build();
        else {
            List<Library> libraries = libraryService.getLibrariesByBook(book);
            log.info("there's {} libraries having book {}", libraries.size(), book.getTitle());
            return ResponseEntity.ok(libraries);
        }
    }

    @PostMapping("/libraries")
    ResponseEntity<?> addLibrary(@Validated @RequestBody Library library, Errors errors, HttpServletRequest request) {

        log.info("about to add library {}", library);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(Objects.requireNonNull(oe.getCode()), new Object[0], locale))
                    .reduce("Error msg:\n", (s1, s2) -> s1 + s2 + "\n");

            log.error("library has errors: {}", errorMessage);

            return ResponseEntity.badRequest().body(errorMessage);
        }

        Library savedLibrary = libraryService.addLibrary(library);
        log.info("library saved {}", savedLibrary);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibrary);
    }
}
