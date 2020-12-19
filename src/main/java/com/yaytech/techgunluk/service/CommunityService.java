package com.yaytech.techgunluk.service;

import com.yaytech.techgunluk.dto.CommunityDto;
import com.yaytech.techgunluk.model.Community;
import com.yaytech.techgunluk.repository.CommunityRepository;
import lombok.AllArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final AuthService authService;

    public List<CommunityDto> getAll(){
        return communityRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private CommunityDto mapToDto(Community community){
        return CommunityDto.builder().name(community.getName())
                .id(community.getId())
                .postCount(community.getPosts().size())
                .build();
    }
    @Transactional
    public CommunityDto save(CommunityDto communityDto){
        Community community = communityRepository.save(mapToCommunity(communityDto));
        communityDto.setId(community.getId());
        return communityDto;
    }

    private Community mapToCommunity(CommunityDto communityDto) {
        return Community.builder().name(communityDto.getName())
                .description(communityDto.getDescription())
                .user(authService.getCurrentUser())
                .createDate(Instant.now())
                .build();
    }

    public  CommunityDto getCommunity(Long id){
        Community community = communityRepository.findById(id).orElseThrow(
                ()->new CommandAcceptanceException("Community not found with "+ id)
        );
        return mapToDto(community);
    }
}
