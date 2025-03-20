package community.jessie_community.controller;

import community.jessie_community.DTO.PostDTO;
import community.jessie_community.domain.Post;
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

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<PostDTO> postList() {
        return postService.findAllPostDTOs();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        try{
            PostDTO onePostDTO = postService.findOnePostDTO(id);
            return ResponseEntity.ok(onePostDTO);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
