package community.jessie_community.repository;

import community.jessie_community.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateCommentRepository implements CommentRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateCommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comment save(Comment comment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("comments").usingGeneratedKeyColumns("comment_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", comment.getUserId());
        parameters.put("post_id", comment.getPostId());
        parameters.put("content", comment.getContent());

        Number key = jdbcInsert.executeAndReturnKey(parameters);
        comment.setId(key.longValue());
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        String sql = "SELECT * FROM comments WHERE comment_id = ?";
        List<Comment> result = jdbcTemplate.query(sql, commentRowMapper(), commentId);
        return result.stream().findAny();
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        String sql = "SELECT * FROM comments WHERE post_id = ? ORDER BY created_at ASC";
        return jdbcTemplate.query(sql, commentRowMapper(), postId);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setId(rs.getLong("comment_id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setPostId(rs.getLong("post_id"));
            comment.setContent(rs.getString("content"));
            comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            comment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return comment;
        };
    }
}
