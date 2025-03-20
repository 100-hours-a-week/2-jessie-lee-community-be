package community.jessie_community.service;

import community.jessie_community.DTO.UserDTO;
import community.jessie_community.domain.User;
import community.jessie_community.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(UserDTO userDTO) {
        // TODO: 중복 검사
        User user = UserDTO.toEntity(userDTO);
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<UserDTO> findUserDTOs() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * id로 회원 한명 조회
     */
    public UserDTO findOneUserDTO(Long id) {
        Optional<User> user = userRepository.findById(id);
        return UserDTO.fromEntity(user.get());
    }
}
