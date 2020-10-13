package com.yaytech.techgunluk.repository;

import com.yaytech.techgunluk.model.Comment;
import com.yaytech.techgunluk.model.Post;
import com.yaytech.techgunluk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommnentRepositoy extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
