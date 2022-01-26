package com.example.userservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username ;
    private String email ;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password ;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> role=new ArrayList<>();

}
