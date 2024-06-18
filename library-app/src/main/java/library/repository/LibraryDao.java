package library.repository;

import library.model.Library;
import library.model.Book;

import java.util.List;

public interface LibraryDao {

    List<Library> findAll();

    Library findById(int id);

    List<Library> findByBook(Book m);

}
