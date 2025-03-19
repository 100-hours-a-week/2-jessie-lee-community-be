package community.jessie_community.repository;

import community.jessie_community.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long commentId);
    List<Comment> findByPostId(Long postId);
}
