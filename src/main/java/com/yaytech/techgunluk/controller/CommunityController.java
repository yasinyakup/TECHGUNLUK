package com.yaytech.techgunluk.controller;

import com.yaytech.techgunluk.model.Community;
import com.yaytech.techgunluk.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommunityController {
    private final CommunityRepository communityRepository;

    @GetMapping
    public List<Community>  getAllCommunities(){
       return communityRepository.findAll();
    }


}

