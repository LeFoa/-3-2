package hello.login.web.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileForm {
    private Long fileId;
    private String fileDescribe;
    private MultipartFile attachFile;
}
