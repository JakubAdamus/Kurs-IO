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

    @Override
    public Library save(Library library) {
        int maxId = SampleData.libraries.stream()
                .min((c1, c2) -> c2.getId() - c1.getId())
                .map(Library::getId)
                .orElse(0);
        library.setId(maxId+1);
        SampleData.libraries.add(library);
        return library;
    }
}
