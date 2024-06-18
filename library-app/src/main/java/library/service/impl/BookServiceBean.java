package library.service.impl;

import library.repository.LibraryDao;
import library.repository.WriterDao;
import library.repository.BookDao;
import library.model.Library;
import library.model.Writer;
import library.model.Book;
import library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private WriterDao writerDao;
    private LibraryDao libraryDao;
    private BookDao bookDao;

    public BookServiceBean(WriterDao writerDao, LibraryDao libraryDao, BookDao bookDao) {
        this.writerDao = writerDao;
        this.libraryDao = libraryDao;
        this.bookDao = bookDao;
    }

    public List<Book> getAllBooks() {
        log.info("searching all books...");
        return bookDao.findAll();
    }

    public List<Book> getBooksByWriter(Writer d) {
        log.info("serching books by writer " + d.getId());
        return bookDao.findByWriter(d);
    }

    public List<Book> getBooksInLibrary(Library c) {
        log.info("searching books in library " + c.getId());
        return bookDao.findByLibrary(c);
    }

    public Book getBookById(int id) {
        log.info("searching book by id " + id);
        return bookDao.findById(id);
    }

    public List<Library> getAllLibraries() {
        log.info("searching all libraries");
        return libraryDao.findAll();
    }

    public List<Library> getLibrariesByBook(Book m) {
        log.info("searching libraries by book " + m.getId());
        return libraryDao.findByBook(m);
    }

    public Library getLibraryById(int id) {
        log.info("searching library by id " + id);
        return libraryDao.findById(id);
    }

    public List<Writer> getAllWriters() {
        log.info("searching all writers");
        return writerDao.findAll();
    }

    public Writer getWriterById(int id) {
        log.info("searching writer by id " + id);
        return writerDao.findById(id);
    }

    @Override
    public Book addBook(Book m) {
        log.info("about to add book " + m);
        return bookDao.add(m);
    }

    @Override
    public Writer addWriter(Writer d) {
        log.info("about to add writer " + d);
        return writerDao.add(d);
    }

    @Autowired
    public void setWriterDao(WriterDao writerDao) {
        this.writerDao = writerDao;
    }
}
