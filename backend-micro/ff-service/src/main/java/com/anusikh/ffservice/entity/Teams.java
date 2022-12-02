package com.anusikh.ffservice.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TEAMS_TBL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_id;
    @NotEmpty
    private String team_name;
    private String team_creator;
    private String team_desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organisations team_org;

    @ElementCollection(targetClass = String.class)
    private List<String> admins;

}
