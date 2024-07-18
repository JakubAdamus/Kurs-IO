package library.repository.data;

import library.model.Book;
import library.model.Library;
import library.model.Writer;
import library.repository.BookDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataBookDao implements BookDao {

    private final BookRepository repository;

    @Override
    public List<Book> findAll() { return repository.findAll();}

    @Override
    public Book findById(int id) { return repository.findById(id).orElse(null);}

    @Override
    public List<Book> findByWriter(Writer d) { return repository.findAllByWriter(d);}

    @Override
    public List<Book> findByLibrary(Library c) { return repository.findAllByLibrariesContaining(c); }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Book add(Book b) { return repository.save(b); }
}
