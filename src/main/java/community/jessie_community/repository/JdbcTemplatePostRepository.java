package community.jessie_community.repository;

import community.jessie_community.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.*;

public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplatePostRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(Post post, Long user_id) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("posts").usingGeneratedKeyColumns("post_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", post.getTitle());
        parameters.put("content", post.getContent());
        parameters.put("image_url", post.getImage_url());

        // like_count, view_count, comment_count는 기본값이 0이기 때문에, 불필요
        // created_at, updated_at은 DB에서 자동으로 CURRENT_TIMESTAMP 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        post.setId(key.longValue());
        post.setUser_id(user_id);
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        List<Post> result = jdbcTemplate.query("SELECT * FROM posts WHERE post_id = ?", postRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM posts", postRowMapper());
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("post_id"));
            post.setUser_id(rs.getLong("user_id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setImage_url(rs.getString("image_url"));
            post.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            post.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
            post.setLike_count(rs.getInt("like_count"));
            post.setView_count(rs.getInt("view_count"));
            post.setComment_count(rs.getInt("comment_count"));
            return post;
        };
    }
}
