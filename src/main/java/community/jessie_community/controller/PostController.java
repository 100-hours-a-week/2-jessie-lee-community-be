package community.jessie_community.controller;

import community.jessie_community.DTO.CommentDTO;
import community.jessie_community.DTO.PostDetailDTO;
import community.jessie_community.DTO.PostSummaryDTO;
import community.jessie_community.service.CommentService;
import community.jessie_community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<PostSummaryDTO> postList() {
        return postService.findAllPostDTOs();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDetailDTO> getPost(@PathVariable("id") Long id) {
        try{
            PostDetailDTO onePostDTO = postService.findOnePostDTO(id);
            return ResponseEntity.ok(onePostDTO);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/posts/{postId}/comments")
    @ResponseBody
    public List<CommentDTO> getComment(@PathVariable("postId") Long id) {
        return commentService.findCommentDTOsByPostId(id);
    }
}
