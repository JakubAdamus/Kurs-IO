package library.web.rest;

import library.model.Library;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
@RequiredArgsConstructor
public class LibraryAdvice {

    private final LibraryValidator validator;

    @InitBinder("library")
    void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}
