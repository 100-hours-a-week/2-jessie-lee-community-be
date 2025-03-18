package community.jessie_community.repository;

import community.jessie_community.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("user_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("password", user.getPassword());
        parameters.put("nickname", user.getNickname());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> result = jdbcTemplate.query("select * from users where user_id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = jdbcTemplate.query("select * from users where email = ?", userRowMapper(), email);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        List<User> result = jdbcTemplate.query("select * from users where nickname = ?", userRowMapper(), nickname);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            return user;
        };
    }
}
