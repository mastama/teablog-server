package tea.tech.teablog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tea.tech.teablog.dto.PostRequest;
import tea.tech.teablog.dto.ResponseService;
import tea.tech.teablog.service.PostService;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseService createPost(@Validated @RequestBody PostRequest request) {
        log.info("Incoming create post {}", request.getAuthor());
        ResponseService responseService = new ResponseService();
        responseService = postService.createPost(request);
        log.info("Outgoing create post {}", request.getAuthor());
        return responseService;
    }
}
