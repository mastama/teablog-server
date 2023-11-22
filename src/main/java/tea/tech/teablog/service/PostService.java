package tea.tech.teablog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tea.tech.teablog.dto.PostRequest;
import tea.tech.teablog.dto.ResponseService;
import tea.tech.teablog.entity.Post;
import tea.tech.teablog.exception.RestParameterNotFoundException;
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

    public ResponseService getPostById(Long id) throws RestParameterNotFoundException {
        log.info("Start get post by id {}", id);
        ResponseService responseService = new ResponseService();
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RestParameterNotFoundException("Post not found with id: " + id));

        //konversi Post ke ResponseService
        responseService.setResponseCode(Constant.RESPONSE.successCode);
        responseService.setResponseDesc(Constant.RESPONSE.approvedDescription);
        responseService.setData(post);

        log.info("End get post by id {}", id);
        return responseService;
    }

    public ResponseService updatePostById(Long id, Post updatePost) throws RestParameterNotFoundException {
        log.info("Start update post by id {}", updatePost.getId());
        ResponseService responseService = new ResponseService();

        if (postRepository.existsById(id)) {
            updatePost.setId(id);
            Post post = postRepository.save(updatePost);

            responseService.setResponseCode(Constant.RESPONSE.successCode);
            responseService.setResponseDesc(Constant.RESPONSE.approvedDescription);
            responseService.setData(post);
        } else {
            throw new RestParameterNotFoundException("Post not found witd id: " + id);
        }
        return responseService;
    }
}
