package community.jessie_community.controller;

import community.jessie_community.domain.Comment;
import community.jessie_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getComment(@PathVariable("postId") Long id) {
        List<Comment> comments = commentService.findCommentsByPostId(id);
        if (!comments.isEmpty()) {
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
