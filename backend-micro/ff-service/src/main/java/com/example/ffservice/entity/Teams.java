package com.example.ffservice.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ENTITY_TBL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_id;
    private String team_name;
    private String team_desc;

    @ElementCollection(targetClass = String.class)
    private List<String> admins;

}
