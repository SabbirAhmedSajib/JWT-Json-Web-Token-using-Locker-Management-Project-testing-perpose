package com.LockerService.DrawerRelease.Service;

import com.LockerService.DrawerRelease.DTO.LSDRWRRELDto;
import com.LockerService.DrawerRelease.DTO.PagenationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LSDRWRRELService {

    LSDRWRRELDto createDrawerRelease(LSDRWRRELDto lsdrwrrelDto);

    PagenationResponse GetAllReleaseInfo(int pageNo, int pageSize, String sortBy, String sortDir);

    List<LSDRWRRELDto> GetAlldrawer();

    LSDRWRRELDto getReleaseByID(long RELID);

    LSDRWRRELDto updateDrawerRelease(LSDRWRRELDto lsdrwrrelDto, long RELID);

    void deleteDrawerById(long RELID);
}
