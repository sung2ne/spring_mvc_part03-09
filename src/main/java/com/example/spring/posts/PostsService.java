package com.example.spring.posts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {
    
    @Autowired
    PostsDao postsDao;

    // 비밀번호 검증
    private boolean verifyPassword(int id, String password) {
        PostsVo posts = postsDao.read(id);
        return posts != null && posts.getPassword().equals(password);
    }

    // 게시글 등록
    public boolean create(PostsVo postsVo) {
        int result = postsDao.create(postsVo);
        return result > 0;
    }

    // 게시글 목록
    public List<PostsVo> list() {
        return postsDao.list();
    }

    // 게시글 보기
    public PostsVo read(int id) {
        return postsDao.read(id);
    }
}
