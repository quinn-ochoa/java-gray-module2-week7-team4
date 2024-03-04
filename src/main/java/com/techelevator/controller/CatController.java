package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    //Provides a Cat Card with the given ID.
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable int id) {
        CatCard card = catCardDao.getCatCardById(id);
        return card;
    }

    //Provides a list of all Cat Cards in the user's collection.
    @RequestMapping(method = RequestMethod.GET)
    public List<CatCard> list() {
        return catCardDao.getCatCards();
    }

    //Provides a new, randomly created Cat Card containing information from the cat fact and picture services.
    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public CatCard getRandom(){
        CatCard card = new CatCard();
        CatFact fact = catFactService.getFact();
        CatPic pic = catPicService.getPic();
        card.setCatFact(fact.getText());
        card.setImgUrl(pic.getFile());
        return card;
    }

    //Saves a card to the user's collection.
    @RequestMapping(method = RequestMethod.POST)
    public CatCard createCard(@Valid @RequestBody CatCard catCard) {
        return catCardDao.createCatCard(catCard);
    }

    //Updates a card in the user's collection.
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public CatCard updateCard(@Valid @RequestBody CatCard catCard, @PathVariable int id) {
        catCard.setCatCardId(id);
        return catCardDao.updateCatCard(catCard);
    }

    //Removes a card from the user's collection.
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public int deleteCard(@PathVariable int id) {
        return catCardDao.deleteCatCardById(id);
    }

}
