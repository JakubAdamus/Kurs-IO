package library.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import library.model.Book;
import library.model.Library;
import library.model.Writer;
import library.repository.BookDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaBookDao implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("select b from Book b")
                .getResultList();
    }

    @Override
    public Book findById(int id) { return entityManager.find(Book.class, id); }

    @Override
    public List<Book> findByWriter(Writer w) {
        return entityManager
                .createQuery("select b from Book b where b.writer=:writer")
                .setParameter("writer", w)
                .getResultList();
    }

    @Override
    public List<Book> findByLibrary(Library l) {
        return entityManager.createQuery(
                "select b from Book b inner join b.libraries library where library=:library")
                .setParameter("library", l)
                .getResultList();
    }

    @Override
    public Book add(Book b) {
        entityManager.persist(b);
        return b;
    }
}
