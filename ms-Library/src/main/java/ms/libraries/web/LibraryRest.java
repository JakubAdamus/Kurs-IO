package ms.libraries.web;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.libraries.dao.LibraryRepository;
import ms.libraries.model.Library;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/webapi")
public class LibraryRest {
    private final LibraryRepository libraryRepository;

    @Value("http://localhost:8080/webapi")
    private String bookServiceUrl;

    @GetMapping("/libraries")
    public List<Library> getLibraries() {
        log.info("about to all libraries");
        List<Library> libraries = libraryRepository.findAll();
        libraries.forEach(this::fillBookNames);
        return libraries;
    }
    @GetMapping("/libraries/{id}/libraries")
    public List<Library> getLibrariesById(@PathVariable int id) {
        log.info("about to libraries by movie id {}", id);
        List<Library> libraries = libraryRepository.findAll(); /////TU COS
        libraries.forEach(this::fillBookNames);
        return libraries;
    }



    private void fillBookNames(Library library) {
        library.getBooks().forEach(bookid->{
            RestTemplate restTemplate = new RestTemplate();
            log.info("about to fill book name {}", bookid);
            ResponseEntity<BookDto> responseEntity = restTemplate.exchange(
                    bookServiceUrl + "/book/" +bookid,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    BookDto.class
            );
            String bookname = responseEntity.getBody().getTitle();
            library.getBookNames().add(bookname);
        });
    }
    @Data
    static class BookDto{
        private int id;
        private String title;
        private String cover;
        private float rating;
    }

}

