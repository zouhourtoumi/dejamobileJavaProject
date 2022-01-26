package com.example.cardservice.service;

import com.example.cardservice.dao.CardRepository;
import com.example.cardservice.entities.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cardService {
    private CardRepository cardRepository;
    public  cardService (CardRepository cardRepository){
        this.cardRepository=cardRepository;
    }

    public List<Card> cardList(){
        return cardRepository.findAll();
    }

    public Card addCard(Card card){
        return cardRepository.save(card);
    }

    public boolean deleteById(Long id) {
        return cardRepository.deleteCardById(id);
    }

    public Card findCardById(Long id){
        return cardRepository.findById(id).get();
    }

}
