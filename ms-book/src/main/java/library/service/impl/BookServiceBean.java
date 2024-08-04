package library.service.impl;

import library.repository.LibraryDao;
import library.repository.WriterDao;
import library.repository.BookDao;
import library.model.Library;
import library.model.Writer;
import library.model.Book;
import library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BookServiceBean implements BookService {

    private static final Logger log = Logger.getLogger(BookService.class.getName());

    private final WriterDao writerDao;
    private final BookDao bookDao;
    private final PlatformTransactionManager transactionManager;

    @Override
    public List<Book> getAllBooks() {
        log.info("searching all books...");
        return bookDao.findAll();
    }

    @Override
    public List<Book> getBooksByWriter(Writer d) {
        log.info("serching books by writer " + d.getId());
        return bookDao.findByWriter(d);
    }

    @Override
    public List<Book> getBooksInLibrary(Library c) {
        log.info("searching books in library " + c.getId());
        return bookDao.findByLibrary(c);
    }

    @Override
    public Book getBookById(int id) {
        log.info("searching book by id " + id);
        return bookDao.findById(id);
    }

    @Override
    public List<Writer> getAllWriters() {
        log.info("searching all writers");
        return writerDao.findAll();
    }

    @Override
    public Writer getWriterById(int id) {
        log.info("searching writer by id " + id);
        return writerDao.findById(id);
    }

//    @Override
//    public Book addBook(Book m) {
//        log.info("about to add book " + m);
//
//        TransactionStatus ts = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        try {
//            m = bookDao.add(m);
//            if(m.getTitle().equals("Custom Book 5")) {
//                throw new RuntimeException("not yet!");
//            }
//            transactionManager.commit(ts);
//        } catch (RuntimeException e) {
//            transactionManager.rollback(ts);
//            throw e;
//        }
//
//        return m;
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Book addBook(Book m) {
        log.info("about to add book " + m);

        m = bookDao.add(m);
        if(m.getTitle().equals("Custom Book 5")) {
            throw new RuntimeException("not yet!");
        }

        return m;
    }

    @Override
    public Writer addWriter(Writer d) {
        log.info("about to add writer " + d);
        return writerDao.add(d);
    }
}
