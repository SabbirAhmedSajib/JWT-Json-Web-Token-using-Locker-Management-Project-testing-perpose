package com.LockerService.DrawerRelease.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "LSDRWRREL"
)
public class LSDRWRRELEntity {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO
    )
    private long RELID;
    private Long LCKRID;
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
