package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public Result create(MultipartHttpServletRequest request) {
        return attachmentService.createAttachment(request);
    }

    @GetMapping("/{fileId}")
    public void get(@PathVariable Integer fileId, HttpServletResponse response) {
        attachmentService.getAttachment(fileId, response);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return attachmentService.deleteAttachment(id);
    }

    @PutMapping("/{id}")
    public Result edit(MultipartHttpServletRequest request, @PathVariable Integer id) {
        return attachmentService.editAttachment(request, id);
    }


}
