package library.repository;

import library.model.Writer;

import java.util.List;

public interface WriterDao {

    List<Writer> findAll();

    Writer findById(int id);

    Writer add(Writer w);


}
