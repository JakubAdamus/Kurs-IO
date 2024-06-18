package library.repository.mem;

import library.repository.WriterDao;
import library.model.Writer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemWriterDao implements WriterDao {
    @Override
    public List<Writer> findAll() {
        return SampleData.writers;
    }

    @Override
    public Writer findById(int id) {
        return SampleData.writers.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Writer add(Writer d) {
        int max = SampleData.writers.stream().max((d1, d2) -> d1.getId() - d2.getId()).get().getId();
        d.setId(++max);
        SampleData.writers.add(d);
        return d;
    }
}
