package community.jessie_community.repository;

import community.jessie_community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository {
}
