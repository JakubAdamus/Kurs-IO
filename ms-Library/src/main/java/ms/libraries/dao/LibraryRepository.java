package ms.libraries.dao;




import ms.libraries.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Integer> {


    List<Library> findAll();

    Library findById(int id);

    Library save(Library library);
}
