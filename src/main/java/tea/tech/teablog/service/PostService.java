package tea.tech.teablog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tea.tech.teablog.dto.PostRequest;
import tea.tech.teablog.dto.PostResponse;
import tea.tech.teablog.entity.Post;
import tea.tech.teablog.repository.PostRepository;

@Service
@Slf4j
public class PostService {
    @Autowired
    PostRepository postRepository;

    public PostResponse createPost(PostRequest request) {
        log.info("Start create post blog {}", request.getAuthor());

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setAuthor(request.getAuthor());

        //save to db
        Post newPost = postRepository.save(post);

        PostResponse postResponse = new PostResponse();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setBody(newPost.getBody());
        postResponse.setAuthor(newPost.getAuthor());
        log.info("TROUBLESHOOT: {}", postResponse);

        log.info("End proses create post blog {}", request.getAuthor());
        return postResponse;
    }
}
