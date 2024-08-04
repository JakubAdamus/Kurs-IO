package library.repository.data;

import library.model.Writer;
import library.repository.WriterDao;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataWriterDao implements WriterDao {

    private final WriterRepository repository;

    @Override
    public List<Writer> findAll() { return repository.findAll(); }

    @Override
    public Writer findById(int id) { return repository.findById(id).orElse(null); }

    @Override
    public Writer add(Writer w) { return repository.save(w); }
}
