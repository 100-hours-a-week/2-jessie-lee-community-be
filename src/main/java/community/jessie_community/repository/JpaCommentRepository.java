package community.jessie_community.repository;

import community.jessie_community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Long>, CommentRepository {
}
