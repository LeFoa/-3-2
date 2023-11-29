package hello.login.domain.file;

import lombok.Data;

@Data
public class File {

    private Long id;
    private String fileDescribe;
    private UploadFile attachFile;

}
