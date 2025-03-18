package community.jessie_community.service;

import community.jessie_community.domain.User;
import community.jessie_community.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach() {
        userRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        User user = new User();
        user.setNickname("newuser");

        // when
        Long saveId = userService.join(user);

        // then
        User findUser = userService.findOne(saveId).get();
        assertThat(user.getNickname()).isEqualTo(findUser.getNickname());
    }

}