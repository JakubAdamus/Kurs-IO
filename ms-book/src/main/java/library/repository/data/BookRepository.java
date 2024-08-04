package library.repository.data;

import library.model.Book;
import library.model.Library;
import library.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByWriter(Writer writer);

    List<Book> findAllByLibrariesContaining(Library library);
}
