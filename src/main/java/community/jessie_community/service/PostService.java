package community.jessie_community.service;

import community.jessie_community.domain.Post;
import community.jessie_community.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 게시글 등록
     */
    public Long post(Post post, Long user_id) {
        post.setUserId(user_id);
        postRepository.save(post);
        return post.getId();
    }

    /**
     * 전체 게시글 조회
     */
    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    /**
     * id로 게시글 하나 조회
     */
    public Optional<Post> findOne(Long id) {
        return postRepository.findById(id);
    }
}
