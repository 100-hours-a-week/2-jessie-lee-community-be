package community.jessie_community.service;

import community.jessie_community.DTO.PostDTO;
import community.jessie_community.domain.Post;
import community.jessie_community.domain.User;
import community.jessie_community.repository.PostRepository;
import community.jessie_community.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * 게시글 등록
     */
    public Long createPost(Post post, Long userId) {
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        post.setUser(user);
        postRepository.save(post);
        return post.getId();
    }

    /**
     * 전체 게시글 조회
     */
    public List<PostDTO> findAllPostDTOs() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * id로 게시글 하나 조회
     */
    public PostDTO findOnePostDTO(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return PostDTO.fromEntity(post.get());
    }
}
