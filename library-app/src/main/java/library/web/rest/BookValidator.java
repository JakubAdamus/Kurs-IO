package library.web.rest;

import library.model.Writer;
import library.service.BookService;
import library.web.rest.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class BookValidator implements Validator {

    private final BookService bookService;

    @Override
    public boolean supports(Class<?> clazz) { return clazz.isAssignableFrom(BookDto.class); }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto book = (BookDto) target;
        Writer writer = bookService.getWriterById(book.getWriterId());
        if (writer == null) {
            errors.rejectValue("writerId", "book.writer.missing");
        }
    }
}
