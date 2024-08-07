package library.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import library.model.Book;
import library.model.Library;
import library.repository.LibraryDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaLibraryDao implements LibraryDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Library> findAll() {
        return entityManager
                .createQuery("select l from Library l")
                .getResultList();
    }

    @Override
    public Library findById(int id) { return entityManager.find(Library.class, id); }

    @Override
    public List<Library> findByBook(Book b) {
        return entityManager
                .createQuery("select l from Library l inner join l.books book where book=:book")
                .setParameter("book", b)
                .getResultList();
    }

    @Override
    public Library save(Library library) {
        entityManager.persist(library);
        return library;
    }
}
