package library.web.rest;

import library.model.Library;
import library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class LibraryValidator implements Validator {

    private final LibraryService libraryService;

    @Override
    public boolean supports(Class<?> clazz) { return clazz.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        Library validatedLibrary = (Library) target;
        boolean duplicated = libraryService.getAllLibraries().stream()
                .anyMatch(library->library.getName().equalsIgnoreCase(validatedLibrary.getName()));
        if (duplicated) {
            errors.rejectValue("name", "library.name.duplicated");
        }
    }
}
