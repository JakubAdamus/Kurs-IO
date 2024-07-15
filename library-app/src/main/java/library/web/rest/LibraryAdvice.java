package library.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice(basePackages = "library.web.rest")
@RequiredArgsConstructor
@Slf4j
public class LibraryAdvice {

    private final LibraryValidator libraryValidator;
    private final BookValidator bookValidator;

    @InitBinder("library")
    void initBinder(WebDataBinder binder) {
        binder.addValidators(libraryValidator);
    }

    @InitBinder("bookDto")
    void initBinderForBook(WebDataBinder binder) {
        binder.addValidators(bookValidator);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        log.error("illegal argument provided", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }
}
