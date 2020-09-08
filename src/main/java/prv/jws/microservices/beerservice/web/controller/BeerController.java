package prv.jws.microservices.beerservice.web.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import prv.jws.microservices.beerservice.web.model.BeerDto;

@RequestMapping("/api/v1/beer")
public interface BeerController {
    @GetMapping(path = "/{beerId}")
    ResponseEntity<BeerDto> getBeerById (@PathVariable("beerId") UUID beerId);

    @PostMapping
    ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto);

    @PutMapping(path = "/{beerId}")
    ResponseEntity updateBeerById(@PathVariable(name="beerId") UUID beerId, @RequestBody BeerDto beerDto);
}
