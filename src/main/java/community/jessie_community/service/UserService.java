package community.jessie_community.service;

import community.jessie_community.domain.User;
import community.jessie_community.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(User user) {
        // TODO: 중복 검사
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    /**
     * id로 회원 한명 조회
     */
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }
}
