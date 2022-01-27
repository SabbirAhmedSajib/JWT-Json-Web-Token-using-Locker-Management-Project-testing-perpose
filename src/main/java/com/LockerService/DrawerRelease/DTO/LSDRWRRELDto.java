package com.LockerService.DrawerRelease.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LSDRWRRELDto {
    private long RELID;
//    @NotEmpty
//    @Size(max = 6, message = "Locker ID should have at least 6 character!")
    private Long LCKRID;
//    @NotEmpty
//    @Size(max = 6, message = "Drawer ID should have at least 6 character!")
    private Long DRWRID;
    private String ACTNUM;
    private String ACTYPE;
    private String DEPPRO;
    private String FORSALVLU;
    private String MARVLU;
    private String CAUMNY;
    private String CUSTPE;
    private String CUSTPEDES;
    private String ADRES1;
    private String ADRES2;
    private String HOUSE;
    private String CITNUM;
    private String TELNUM;
    private String ALLODAT;
    private String EXPDAT;
    private String STATUS;
    private String REMARKS;
}
