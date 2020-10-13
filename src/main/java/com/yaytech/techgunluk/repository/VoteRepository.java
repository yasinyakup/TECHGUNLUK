package com.yaytech.techgunluk.repository;

import com.yaytech.techgunluk.model.Post;
import com.yaytech.techgunluk.model.User;
import com.yaytech.techgunluk.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    //Optional<Vote> findTopByPostAndUserOrderByUserIdDesc(User user, Post post);
}
