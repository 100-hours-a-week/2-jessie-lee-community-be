package community.jessie_community.service;

import community.jessie_community.domain.Comment;
import community.jessie_community.repository.CommentRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * 댓글 등록
     */
    public Long createComment(Comment comment, Long userId, Long postId){
        comment.setUserId(userId);
        comment.setPostId(postId);
        commentRepository.save(comment);
        return comment.getId();
    }

    /**
     * 게시글 id 에 따른 댓글 조회
     */
    public List<Comment> findCommentsByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }
}
