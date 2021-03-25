package uz.pdp.warehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.AttachmentContent;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentContentRepository;
import uz.pdp.warehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {


    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result createAttachment(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setAttachment(saveAttachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);
        return new Result("Fayl saqlandi", true, attachment.getId());
    }

    @SneakyThrows
    public void getAttachment(Integer fileId, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(fileId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = new Attachment();

            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(fileId);
            if (byAttachmentId.isPresent()) {
                AttachmentContent attachmentContent = new AttachmentContent();
                response.setHeader("Content-Disposition", "attachment; filename=\" " + attachment.getName() + " \"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
            }
        }
    }

    public Result deleteAttachment(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Bunday ID li file yo`q", false);

        attachmentRepository.deleteById(id);
        attachmentContentRepository.deleteByAttachmentId(id);
        return new Result("file o`chirildi", true);
    }

}
