package library.repository.mem;

import library.repository.BookDao;
import library.model.Library;
import library.model.Writer;
import library.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class MemBookDao implements BookDao {
    @Override
    public List<Book> findAll() {
        return SampleData.books;
    }

    @Override
    public Book findById(int id) {
        return SampleData.books.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Book> findByWriter(Writer d) {
       return SampleData.books.stream().filter(m -> m.getWriter() == d).collect(Collectors.toList());
    }

    @Override
    public List<Book> findByLibrary(Library c) {
        return SampleData.books.stream().filter(m -> m.getLibraries().contains(c)).collect(Collectors.toList());
    }

    @Override
    public Book add(Book m) {
        int max = SampleData.books.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        m.setId(++max);
        SampleData.books.add(m);
        return m;
    }
}
