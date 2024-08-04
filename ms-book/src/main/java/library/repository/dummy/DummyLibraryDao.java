package library.repository.dummy;

import library.model.Book;
import library.model.Library;
import library.repository.LibraryDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyLibraryDao implements LibraryDao {

    @Override
    public List<Library> findAll() {
        return null;
    }

    @Override
    public Library findById(int id) {
        return null;
    }

    @Override
    public List<Library> findByBook(Book m) {
        return null;
    }

    @Override
    public Library save(Library library) {return null; }
}
