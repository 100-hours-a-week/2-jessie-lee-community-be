package community.jessie_community.repository;

import community.jessie_community.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryUserRepositoryTest {
    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        User user = new User();
        user.setEmail("example@example.com");
        user.setNickname("jiye");
        user.setPassword("1234");

        // when
        repository.save(user);

        // then
        User result = repository.findById(user.getId()).get();
        assertThat(user).isEqualTo(result);
    }

    @Test
    public void findByEmail() {
        // given
        User user1 = new User();
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setEmail("user2@example.com");

        // when
        repository.save(user1);
        repository.save(user2);

        // then
        User result = repository.findByEmail("user1@example.com").get();
        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findByNickname() {
        // given
        User user1 = new User();
        user1.setNickname("user1");

        User user2 = new User();
        user2.setNickname("user2");

        // when
        repository.save(user1);
        repository.save(user2);

        // then
        User result = repository.findByNickname("user1").get();
        assertThat(result).isEqualTo(user1);
    }

    @Test
    public void findAll() {
        // given
        User user1 = new User();
        user1.setNickname("user1");

        User user2 = new User();
        user2.setNickname("user2");

        // when
        repository.save(user1);
        repository.save(user2);

        // then
        List<User> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}