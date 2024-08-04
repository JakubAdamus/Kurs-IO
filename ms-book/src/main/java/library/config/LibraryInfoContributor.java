package library.config;

import library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LibraryInfoContributor implements InfoContributor {

        private final BookService bookService;

        @Override
        public void contribute(org.springframework.boot.actuate.info.Info.Builder builder) {
            builder.withDetail("number of books", bookService.getAllBooks().size());
        }
}
