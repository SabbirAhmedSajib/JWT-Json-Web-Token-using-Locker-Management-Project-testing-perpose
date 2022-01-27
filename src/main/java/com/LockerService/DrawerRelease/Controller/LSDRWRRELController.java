package com.LockerService.DrawerRelease.Controller;

import com.LockerService.DrawerRelease.DTO.LSDRWRRELDto;
import com.LockerService.DrawerRelease.DTO.PagenationResponse;
import com.LockerService.DrawerRelease.Service.LSDRWRRELService;
import com.LockerService.DrawerRelease.Utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Release")
public class LSDRWRRELController {

    @Autowired
    private LSDRWRRELService lsdrwrrelService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/Create")
    public ResponseEntity<LSDRWRRELDto> createDRAWERRelease(@Valid @RequestBody LSDRWRRELDto lsdrwrrelDto){
        return new ResponseEntity<>(lsdrwrrelService.createDrawerRelease(lsdrwrrelDto), HttpStatus.CREATED);

    }

    @GetMapping("getAll")
    public PagenationResponse GetAllRelease(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return lsdrwrrelService.GetAllReleaseInfo(pageNo,pageSize, sortBy, sortDir);
    }

    @GetMapping("/get")
    public List<LSDRWRRELDto> GetRelease(){
        return lsdrwrrelService.GetAlldrawer();
    }

    @GetMapping("/{RELID}")
    public ResponseEntity<LSDRWRRELDto> getDrawerReleaseById(@PathVariable(name = "RELID") long RELID){
        return ResponseEntity.ok(lsdrwrrelService.getReleaseByID(RELID));

    }

    //update post by id rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{RELID}")
    public  ResponseEntity<LSDRWRRELDto> updatePost(@RequestBody LSDRWRRELDto lsdrwrrelDto, @PathVariable(name = "RELID") long RELID ){
        LSDRWRRELDto DrawerResponse = lsdrwrrelService.updateDrawerRelease(lsdrwrrelDto,RELID);
        return new ResponseEntity<>(DrawerResponse,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // delete post rest api
    @DeleteMapping("/{RELID}")
    public ResponseEntity<String> deleteDrawerRelease(@PathVariable(name = "RELID") long RELID){

        lsdrwrrelService.deleteDrawerById(RELID);

        return new ResponseEntity<>("Drawer Release  successfully.", HttpStatus.OK);
    }
}
