package com.yaytech.techgunluk.controller;

import com.yaytech.techgunluk.dto.CommunityDto;
import com.yaytech.techgunluk.model.Community;
import com.yaytech.techgunluk.repository.CommunityRepository;
import com.yaytech.techgunluk.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/community")
public class CommunityController {
   private final CommunityService communityService;
    @GetMapping
    public List<CommunityDto>  getAllCommunities(){
       return communityService.getAll();
    }

    @GetMapping("/{id}")
    public CommunityDto getCommunity(@PathVariable Long id){
        return communityService.getCommunity(id);
    }

    @PostMapping("/puosts")
    public CommunityDto create(@RequestBody @Valid CommunityDto communityDto){
        return communityService.save(communityDto);
    }


}

