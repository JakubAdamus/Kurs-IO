package library.web.rest;

import library.model.Library;
import library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LibraryRest {

    private final LibraryService libraryService;

    @GetMapping("/libraries")
    List<Library> getLibraries() {
        log.info("about to retrieve libraries list");
        List<Library> libraries = libraryService.getAllLibraries();
        log.info("{} libraries found", libraries.size());
        return libraries;
    }
}
