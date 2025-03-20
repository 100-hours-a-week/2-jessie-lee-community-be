package community.jessie_community.repository;

import community.jessie_community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
    @Override
    Optional<User> findByNickname(String nickname);
}
