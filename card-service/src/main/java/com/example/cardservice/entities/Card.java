package com.example.cardservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Data @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Card {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name ;
    private double code;

}
