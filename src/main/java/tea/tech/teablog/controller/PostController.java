package tea.tech.teablog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tea.tech.teablog.dto.PostRequest;
import tea.tech.teablog.dto.ResponseService;
import tea.tech.teablog.exception.RestParameterNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseService getPostById(@PathVariable Long id) throws RestParameterNotFoundException {
        log.info("Incoming get post by id {}", id);
        ResponseService responseService = new ResponseService();
        responseService = postService.getPostById(id);
        log.info("Outgoing get post by id {}", id);
        return responseService;
    }
}
