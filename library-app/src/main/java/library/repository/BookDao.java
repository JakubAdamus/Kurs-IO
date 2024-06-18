package library.repository;

import library.model.Library;
import library.model.Writer;
import library.model.Book;

import java.util.List;

public interface BookDao {

    List<Book> findAll();

    Book findById(int id);

    List<Book> findByWriter(Writer d);

    List<Book> findByLibrary(Library c);

    Book add(Book m);

}
