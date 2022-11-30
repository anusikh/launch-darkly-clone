package com.example.ffservice.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORG_TBL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organisations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long org_id;
    @NotEmpty
    private String org_name;
    private String org_creator;
    private Date org_creation_date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team_org")
    private List<Teams> teams;

}
