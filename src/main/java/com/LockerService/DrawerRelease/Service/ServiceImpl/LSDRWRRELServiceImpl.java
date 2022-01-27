package com.LockerService.DrawerRelease.Service.ServiceImpl;

import com.LockerService.DrawerRelease.DTO.LSDRWRRELDto;
import com.LockerService.DrawerRelease.DTO.PagenationResponse;
import com.LockerService.DrawerRelease.Entity.LSDRWRRELEntity;
import com.LockerService.DrawerRelease.Exception.ResourceNotFoundException;
import com.LockerService.DrawerRelease.Repository.LSDRWRRELRepo;
import com.LockerService.DrawerRelease.Service.LSDRWRRELService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LSDRWRRELServiceImpl implements LSDRWRRELService {


    private LSDRWRRELRepo lsdrwrrelRepo;
    private ModelMapper mapper;

    public LSDRWRRELServiceImpl(LSDRWRRELRepo lsdrwrrelRepo, ModelMapper mapper) {
        this.lsdrwrrelRepo = lsdrwrrelRepo;
        this.mapper= mapper;
    }

    @Override
    public LSDRWRRELDto createDrawerRelease(LSDRWRRELDto lsdrwrrelDto) {
        LSDRWRRELEntity lsdrwrrelEntity=mapToEntity(lsdrwrrelDto);
        LSDRWRRELEntity newRelease=lsdrwrrelRepo.save(lsdrwrrelEntity);

        //convert entity to DTO
        LSDRWRRELDto ReleaseResponse = mapToDTO(newRelease);
        return ReleaseResponse;
    }

    @Override
    public List<LSDRWRRELDto> GetAlldrawer() {
        List<LSDRWRRELEntity> lsdrwrrelEntities= lsdrwrrelRepo.findAll();
        return lsdrwrrelEntities.stream().map
                (lsdrwrrelEntity -> mapToDTO(lsdrwrrelEntity)).collect(Collectors.toList());

    }

    @Override
    public LSDRWRRELDto getReleaseByID(long RELID) {
        LSDRWRRELEntity lsdrwrrelEntity=lsdrwrrelRepo.findById(RELID).orElseThrow(() -> new ResourceNotFoundException("LSDRWRRELEntity","RELID",RELID));
        return mapToDTO(lsdrwrrelEntity);

    }

    @Override
    public LSDRWRRELDto updateDrawerRelease(LSDRWRRELDto lsdrwrrelDto, long RELID) {
        LSDRWRRELEntity lsdrwrrelEntity=lsdrwrrelRepo.findById(RELID).orElseThrow(() -> new ResourceNotFoundException("LSDRWRRELEntity","RELID",RELID));

        lsdrwrrelEntity.setRELID(lsdrwrrelDto.getRELID());
        lsdrwrrelEntity.setLCKRID(lsdrwrrelDto.getLCKRID());
        lsdrwrrelEntity.setDRWRID(lsdrwrrelDto.getDRWRID());
        lsdrwrrelEntity.setACTNUM(lsdrwrrelDto.getACTNUM());
        lsdrwrrelEntity.setACTYPE(lsdrwrrelDto.getACTYPE());
        lsdrwrrelEntity.setDEPPRO(lsdrwrrelDto.getDEPPRO());
        lsdrwrrelEntity.setFORSALVLU(lsdrwrrelDto.getFORSALVLU());
        lsdrwrrelEntity.setMARVLU(lsdrwrrelDto.getMARVLU());
        lsdrwrrelEntity.setCAUMNY(lsdrwrrelDto.getCAUMNY());
        lsdrwrrelEntity.setCUSTPE(lsdrwrrelDto.getCUSTPE());
        lsdrwrrelEntity.setCUSTPEDES(lsdrwrrelDto.getCUSTPEDES());
        lsdrwrrelEntity.setADRES1(lsdrwrrelDto.getADRES1());
        lsdrwrrelEntity.setADRES2(lsdrwrrelDto.getADRES2());
        lsdrwrrelEntity.setHOUSE(lsdrwrrelDto.getHOUSE());
        lsdrwrrelEntity.setCITNUM(lsdrwrrelDto.getCITNUM());
        lsdrwrrelEntity.setTELNUM(lsdrwrrelDto.getTELNUM());
        lsdrwrrelEntity.setALLODAT(lsdrwrrelDto.getALLODAT());
        lsdrwrrelEntity.setEXPDAT(lsdrwrrelDto.getEXPDAT());
        lsdrwrrelEntity.setSTATUS(lsdrwrrelDto.getSTATUS());
        lsdrwrrelEntity.setREMARKS(lsdrwrrelDto.getREMARKS());

        LSDRWRRELEntity updateRelease=lsdrwrrelRepo.save(lsdrwrrelEntity);
        return mapToDTO(updateRelease);
    }

    @Override
    public void deleteDrawerById(long RELID) {
        // get post by id from the database
        LSDRWRRELEntity lsdrwrrelEntity = lsdrwrrelRepo.findById(RELID).orElseThrow(() -> new ResourceNotFoundException("Post", "RELID", RELID));
        lsdrwrrelRepo.delete(lsdrwrrelEntity);
    }


    @Override
        public PagenationResponse GetAllReleaseInfo(int pageNo, int pageSize, String sortBy, String sortDir) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
                    Sort.by(sortBy).descending();

            // create Pageable instance
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

            Page<LSDRWRRELEntity> lsdrwrrelEntities = lsdrwrrelRepo.findAll(pageable);

            // get content for page object
            List<LSDRWRRELEntity> listOfRelease = lsdrwrrelEntities.getContent();

            List<LSDRWRRELDto> content= listOfRelease.stream().map(lsdrwrrelEntitiy -> mapToDTO(lsdrwrrelEntitiy)).collect(Collectors.toList());

            PagenationResponse pagenationResponse = new PagenationResponse();
            pagenationResponse.setContent(content);
            pagenationResponse.setPageNo(lsdrwrrelEntities.getNumber());
            pagenationResponse.setPageSize(lsdrwrrelEntities.getSize());
            pagenationResponse.setTotalElements(lsdrwrrelEntities.getTotalElements());
            pagenationResponse.setTotalPages(lsdrwrrelEntities.getTotalPages());
            pagenationResponse.setLast(lsdrwrrelEntities.isLast());

            return pagenationResponse;
        }

    //Convert Entity into DTO
    private LSDRWRRELDto mapToDTO(LSDRWRRELEntity lsdrwrrelEntity){

        LSDRWRRELDto lsdrwrrelDto = mapper.map(lsdrwrrelEntity, LSDRWRRELDto.class);
        return lsdrwrrelDto;

      /*  LSDRWRRELDto lsdrwrrelDto = new LSDRWRRELDto();
        lsdrwrrelDto.setRELID(lsdrwrrelEntity.getRELID());
        lsdrwrrelDto.setLCKRID(lsdrwrrelEntity.getLCKRID());
        lsdrwrrelDto.setDRWRID(lsdrwrrelEntity.getDRWRID());
        lsdrwrrelDto.setACTNUM(lsdrwrrelEntity.getACTNUM());
        lsdrwrrelDto.setACTYPE(lsdrwrrelEntity.getACTYPE());
        lsdrwrrelDto.setDEPPRO(lsdrwrrelEntity.getDEPPRO());
        lsdrwrrelDto.setFORSALVLU(lsdrwrrelEntity.getFORSALVLU());
        lsdrwrrelDto.setMARVLU(lsdrwrrelEntity.getMARVLU());
        lsdrwrrelDto.setCAUMNY(lsdrwrrelEntity.getCAUMNY());
        lsdrwrrelDto.setCUSTPE(lsdrwrrelEntity.getCUSTPE());
        lsdrwrrelDto.setCUSTPEDES(lsdrwrrelEntity.getCUSTPEDES());
        lsdrwrrelDto.setADRES1(lsdrwrrelEntity.getADRES1());
        lsdrwrrelDto.setADRES2(lsdrwrrelEntity.getADRES2());
        lsdrwrrelDto.setHOUSE(lsdrwrrelEntity.getHOUSE());
        lsdrwrrelDto.setCITNUM(lsdrwrrelEntity.getCITNUM());
        lsdrwrrelDto.setTELNUM(lsdrwrrelEntity.getTELNUM());
        lsdrwrrelDto.setALLODAT(lsdrwrrelEntity.getALLODAT());
        lsdrwrrelDto.setEXPDAT(lsdrwrrelEntity.getEXPDAT());
        lsdrwrrelDto.setSTATUS(lsdrwrrelEntity.getSTATUS());
        lsdrwrrelDto.setREMARKS(lsdrwrrelEntity.getREMARKS());


        return lsdrwrrelDto;
        */

    }

    //Converted DTO to Entity
    private LSDRWRRELEntity mapToEntity(LSDRWRRELDto lsdrwrrelDto){

        LSDRWRRELEntity lsdrwrrelEntity = mapper.map(lsdrwrrelDto,LSDRWRRELEntity.class);
        return lsdrwrrelEntity;

      /*  LSDRWRRELEntity lsdrwrrelEntity = new LSDRWRRELEntity();

        lsdrwrrelEntity.setRELID(lsdrwrrelDto.getRELID());
        lsdrwrrelEntity.setLCKRID(lsdrwrrelDto.getLCKRID());
        lsdrwrrelEntity.setDRWRID(lsdrwrrelDto.getDRWRID());
        lsdrwrrelEntity.setACTNUM(lsdrwrrelDto.getACTNUM());
        lsdrwrrelEntity.setACTYPE(lsdrwrrelDto.getACTYPE());
        lsdrwrrelEntity.setDEPPRO(lsdrwrrelDto.getDEPPRO());
        lsdrwrrelEntity.setFORSALVLU(lsdrwrrelDto.getFORSALVLU());
        lsdrwrrelEntity.setMARVLU(lsdrwrrelDto.getMARVLU());
        lsdrwrrelEntity.setCAUMNY(lsdrwrrelDto.getCAUMNY());
        lsdrwrrelEntity.setCUSTPE(lsdrwrrelDto.getCUSTPE());
        lsdrwrrelEntity.setCUSTPEDES(lsdrwrrelDto.getCUSTPEDES());
        lsdrwrrelEntity.setADRES1(lsdrwrrelDto.getADRES1());
        lsdrwrrelEntity.setADRES2(lsdrwrrelDto.getADRES2());
        lsdrwrrelEntity.setHOUSE(lsdrwrrelDto.getHOUSE());
        lsdrwrrelEntity.setCITNUM(lsdrwrrelDto.getCITNUM());
        lsdrwrrelEntity.setTELNUM(lsdrwrrelDto.getTELNUM());
        lsdrwrrelEntity.setALLODAT(lsdrwrrelDto.getALLODAT());
        lsdrwrrelEntity.setEXPDAT(lsdrwrrelDto.getEXPDAT());
        lsdrwrrelEntity.setSTATUS(lsdrwrrelDto.getSTATUS());
        lsdrwrrelEntity.setREMARKS(lsdrwrrelDto.getREMARKS());

        return lsdrwrrelEntity;
        */
    }
}
