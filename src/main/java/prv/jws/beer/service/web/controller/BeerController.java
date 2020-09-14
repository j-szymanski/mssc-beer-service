package prv.jws.beer.service.web.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import prv.jws.beer.service.web.model.BeerDto;
import prv.jws.beer.service.web.model.BeerPagedList;
import prv.jws.beer.service.web.model.BeerStyle;

@RequestMapping("/api/v1/beers")
public interface BeerController {

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<BeerPagedList> getBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                           @RequestParam(value = "beerName", required = false) String beerName,
                                           @RequestParam(value = "beerStyle", required = false) BeerStyle beerStyle,
                                           @RequestParam(value = "showInventoryOnHand", required = false) Boolean showOnHand);

    @GetMapping(path = "/{beerId}")
    ResponseEntity<BeerDto> getBeerById (@PathVariable("beerId") UUID beerId,
                                         @RequestParam(value = "showInventoryOnHand", required = false) Boolean showOnHand);

    @PostMapping
    ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto);

    @PutMapping(path = "/{beerId}")
    ResponseEntity updateBeerById(@PathVariable(name="beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto);
}
