package prv.jws.beer.service.web.controller;

import javax.validation.Valid;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import prv.jws.beer.service.web.model.BeerDto;

@RequestMapping("/api/v1/beers")
public interface BeerController {
    @GetMapping(path = "/{beerId}")
    ResponseEntity<BeerDto> getBeerById (@PathVariable("beerId") UUID beerId);

    @PostMapping
    ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto);

    @PutMapping(path = "/{beerId}")
    ResponseEntity updateBeerById(@PathVariable(name="beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto);
}
