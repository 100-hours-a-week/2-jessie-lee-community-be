package community.jessie_community;

import community.jessie_community.repository.*;
import community.jessie_community.service.CommentService;
import community.jessie_community.service.PostService;
import community.jessie_community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository);
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository, userRepository, commentRepository);
    }

    @Bean
    public CommentService commentService() {
        return new CommentService(commentRepository, userRepository, postRepository);
    }
}
