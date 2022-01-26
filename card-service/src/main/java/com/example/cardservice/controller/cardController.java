package com.example.cardservice.controller;

import com.example.cardservice.CardServiceApplication;
import com.example.cardservice.dao.CardRepository;
import com.example.cardservice.entities.Card;
import com.example.cardservice.service.cardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class cardController {

    private CardRepository cardRepository;
    private com.example.cardservice.service.cardService cardService;

    public cardController(CardRepository cardRepository){
        this.cardRepository=cardRepository;
    }

    public  cardController( com.example.cardservice.service.cardService cardService){
        this.cardService=cardService;
    }
    @GetMapping(path ="/cards" )
    public List<Card> cardsList(){
        return cardService.cardList();
    }

    @GetMapping(path ="/cards/{id}" )
    public Card cardById(@PathVariable (name = "id")Long idCard){
        return cardService.findCardById(idCard);
    }

    @PostMapping(path = "/cards")
    public Card addCard(@RequestBody Card card){
        return cardService.addCard(card);
    }

    @PutMapping(path = "/cards/{id}")
    public Card addCard(@PathVariable Long id,@RequestBody Card card){
        card.setId(id);
        return cardService.addCard(card);
    }

    @DeleteMapping(path = "/cards/{id}")
    public boolean delete(@PathVariable Long id){
        return cardService.deleteById(id);
    }

}
