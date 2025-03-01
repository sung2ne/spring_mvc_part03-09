package com.example.spring.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 게시글 등록
    public int create(PostsVo postsVo) {
        String query = "INSERT INTO POSTS (TITLE, CONTENT, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
        int result = -1;

        try {
            result = jdbcTemplate.update(query, postsVo.getTitle(), postsVo.getContent(), postsVo.getUsername(), postsVo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 게시글 목록
    public List<PostsVo> list() {
        String query = "SELECT * FROM POSTS";
        List<PostsVo> postsVoList = null;

        try {
            postsVoList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PostsVo.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postsVoList;
    }

    // 게시글 보기
    public PostsVo read(int id) {
        String query = "SELECT * FROM POSTS WHERE ID = ?";
        PostsVo postsVo = null;

        try {
            postsVo = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(PostsVo.class), id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postsVo;
    }
}
