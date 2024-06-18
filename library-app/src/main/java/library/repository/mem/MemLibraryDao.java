package library.repository.mem;

import library.repository.LibraryDao;
import library.model.Library;
import library.model.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("libraryDao")
//@Component
//@Primary
public class MemLibraryDao implements LibraryDao {

    @Override
    public List<Library> findAll() {
        return SampleData.libraries;
    }

    @Override
    public Library findById(int id) {
        return SampleData.libraries.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Library> findByBook(Book m) {
        return SampleData.libraries.stream().filter(c -> c.getBooks().contains(m)).collect(Collectors.toList());
    }
}
