package com.yaytech.techgunluk.repository;

import com.yaytech.techgunluk.model.Community;
import com.yaytech.techgunluk.model.Post;
import com.yaytech.techgunluk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByCommunity(Community community);

    List<Post> findByUser(User user);
}
