package com.argus.mytodo.entities.mappers;

import com.argus.mytodo.entities.Authority;
import com.argus.mytodo.entities.dtos.AuthorityDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AuthorityMapper {

    public Authority mapFromRest(AuthorityDto authorityDto){
        Authority authority = new Authority();
        if(authorityDto.getId() != null ) authority.setId(authorityDto.getId());
        if(authorityDto.getUuid() != null ) authority.setUuid(authorityDto.getUuid());
        if(authorityDto.getName() != null ) authority.setName(authorityDto.getName());
        return authority;
    }

    public AuthorityDto mapTorest(Authority authority){
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setUuid(authority.getUuid());
        authorityDto.setCreationDate(authority.getCreationDate());
        authorityDto.setLastUpdate(authority.getLastUpdate());
        authorityDto.setName(authority.getName());
        return authorityDto;
    }
}
