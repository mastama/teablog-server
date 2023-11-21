package tea.tech.teablog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tea.tech.teablog.dto.PostRequest;
import tea.tech.teablog.dto.ResponseService;
import tea.tech.teablog.entity.Post;
import tea.tech.teablog.repository.PostRepository;
import tea.tech.teablog.util.Constant;

@Service
@Slf4j
public class PostService {
    @Autowired
    PostRepository postRepository;

    public ResponseService createPost(PostRequest request) {
        log.info("Start create post blog {}", request.getAuthor());
        ResponseService responseService = new ResponseService();

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setAuthor(request.getAuthor());

        //save to db
        Post newPost = postRepository.save(post);

        responseService.setResponseCode(Constant.RESPONSE.successCode);
        responseService.setResponseDesc(Constant.RESPONSE.approvedDescription);
        responseService.setData(newPost);

        log.info("End proses create post blog {}", request.getAuthor());
        return responseService;
    }
}
