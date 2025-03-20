package community.jessie_community.service;

import community.jessie_community.DTO.CommentDTO;
import community.jessie_community.domain.Comment;
import community.jessie_community.domain.Post;
import community.jessie_community.domain.User;
import community.jessie_community.repository.CommentRepository;
import community.jessie_community.repository.PostRepository;
import community.jessie_community.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * 댓글 등록
     */
    public Long createComment(Comment comment, Long userId, Long postId){
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
        Post post = postRepository.findById(postId)
                        .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + postId));
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return comment.getId();
    }

    /**
     * 게시글 id 에 따른 댓글 조회
     */
    public List<CommentDTO> findCommentDTOsByPostId(Long postId){
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(CommentDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
