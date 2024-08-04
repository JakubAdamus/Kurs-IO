package library.repository.data;

import library.model.Book;
import library.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    List<Library> findAllByNameContaining(String name);

    @Query("select l from Library l inner join l.books book where book=:book")
    List<Library> findAllByMovie(@Param("book") Book book);
}
