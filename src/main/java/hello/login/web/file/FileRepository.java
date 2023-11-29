package hello.login.web.file;

import hello.login.domain.file.File;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FileRepository {
    private final static Map<Long, File> store = new HashMap<>();
    private long sequence =0L;

    public File save(File file){
        file.setId(++sequence);
        store.put(file.getId(),file);
        return file;
    }

    public File findById(Long id){
        return store.get(id);
    }
}
