package com.example.cardservice.dao;

import com.example.cardservice.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card,Long > {
    boolean deleteCardById(Long id);
}
