package com.example.spring.posts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostsDao {
    private static final Logger logger = LoggerFactory.getLogger(PostsDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 게시글 등록
    public int create(PostsVo postsVo) {
        String query = "INSERT INTO POSTS (TITLE, CONTENT, USERNAME, PASSWORD) VALUES (?, ?, ?, ?)";
        int result = -1;

        try {
            result = jdbcTemplate.update(query, postsVo.getTitle(), postsVo.getContent(), postsVo.getUsername(), postsVo.getPassword());
        } catch (DataAccessException e) {
            logger.error("게시글 등록, 오류 발생 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 게시글 목록
    public List<PostsVo> list() {
        String query = "SELECT * FROM POSTS";
        List<PostsVo> postsVoList = null;

        try {
            postsVoList = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PostsVo.class));
        } catch (DataAccessException e) {
            logger.error("게시글 목록, 오류 발생 : {}", e.getMessage(), e);
        }

        return postsVoList;
    }

    // 게시글 보기
    public PostsVo read(int id) {
        String query = "SELECT * FROM POSTS WHERE ID = ? LIMIT 1";
        PostsVo postsVo = null;

        try {
            postsVo = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(PostsVo.class), id);
        } catch (DataAccessException e) {
            logger.error("게시글 보기, 오류 발생 : {}", e.getMessage(), e);
        }

        return postsVo;
    }
}