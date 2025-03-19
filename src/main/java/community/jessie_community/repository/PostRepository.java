package community.jessie_community.repository;

import community.jessie_community.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post, Long user_id);
    Optional<Post> findById(Long id);
    List<Post> findAll();
}
