package hello.login.web.file;

import hello.login.domain.file.File;
import hello.login.domain.file.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;
    private final FileStore fileStore;

    @GetMapping("/service-detect")
    public String detectService(){
        return "service/detect-form";
    }

    @PostMapping("/service-detect")
    public String saveFile(@ModelAttribute FileForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());

        File file = new File();
        file.setFileDescribe(form.getFileDescribe());
        file.setAttachFile(attachFile);
        fileRepository.save(file);

        redirectAttributes.addAttribute("fileId",file.getId());

        return "redirect:/detect/files/{fileId}";
    }

    @GetMapping("/detect/files/{id}")
    public String files(@PathVariable Long id, Model model){
        File file = fileRepository.findById(id);
        model.addAttribute("file",file);
        return "service/detect-view";
    }

    @GetMapping("/detect/file/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws MalformedURLException {
        File file = fileRepository.findById(fileId);
        String storeFileName = file.getAttachFile().getStoreFileName();
        String uploadFileName = file.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        log.info("uploadFileName={}", uploadFileName);

        String encodedUploadFileName = UriUtils.encode(uploadFileName,
                StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" +
                encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
