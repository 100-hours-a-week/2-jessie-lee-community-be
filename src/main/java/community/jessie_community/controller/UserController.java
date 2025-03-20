package community.jessie_community.controller;

import community.jessie_community.DTO.UserDTO;
import community.jessie_community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserDTO> usersList() {
        return userService.findUserDTOs();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        try{
            UserDTO oneUserDTO = userService.findOneUserDTO(id);
            return ResponseEntity.ok(oneUserDTO);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
