package community.jessie_community.controller;

import community.jessie_community.DTO.CommentDTO;
import community.jessie_community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/posts/{postId}/comments")
    @ResponseBody
    public List<CommentDTO> getComment(@PathVariable("postId") Long id) {
        return commentService.findCommentDTOsByPostId(id);
    }
}
