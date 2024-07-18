package library.service.impl;

import library.model.Library;
import library.model.Book;
import library.repository.LibraryDao;
import library.repository.BookDao;
import library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Scope("prototype")
public class LibraryServiceBean implements LibraryService {

    private static final Logger log = Logger.getLogger(LibraryService.class.getName());

    private LibraryDao libraryDao;
    private BookDao bookDao;

    @Autowired
    public LibraryServiceBean(LibraryDao libraryDao, BookDao bookDao) {
        log.info("creating library service bean");
        this.libraryDao = libraryDao;
        this.bookDao = bookDao;
    }

    @Override
    public Library getLibraryById(int id) {
        log.info("searching library by id " + id);
        return libraryDao.findById(id);
    }

    @Override
    public List<Book> getBooksInLibrary(Library c) {
        log.info("searching books played in cinema " + c.getId());
        return bookDao.findByLibrary(c);
    }

    @Override
    public List<Library> getAllLibraries() {
        log.info("searching all libraries");
        return libraryDao.findAll();
    }

    @Override
    public List<Library> getLibrariesByBook(Book m) {
        log.info("searching libraries by book " + m.getId());
        return libraryDao.findByBook(m);
    }

    @Override
    public Library addLibrary(Library library) {
        log.info("adding new library " + library);
        return libraryDao.save(library);
    }
}
