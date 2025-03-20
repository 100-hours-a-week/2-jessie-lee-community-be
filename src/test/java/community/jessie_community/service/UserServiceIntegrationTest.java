package community.jessie_community.service;

import community.jessie_community.DTO.UserDTO;
import community.jessie_community.domain.User;
import community.jessie_community.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 회원가입() {
        // given
        UserDTO user = new UserDTO();
        user.setEmail("newuser@example.com");
        user.setPassword("1234");
        user.setNickname("newuser");

        // when
        Long saveId = userService.join(user);

        // then
        UserDTO findUser = userService.findOneUserDTO(saveId);
        assertThat(user.getNickname()).isEqualTo(findUser.getNickname());
    }

}
