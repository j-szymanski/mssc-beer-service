package prv.jws.microservices.beerservice.web.controller.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import prv.jws.microservices.beerservice.web.controller.BeerController;
import prv.jws.microservices.beerservice.web.model.BeerDto;

@RestController
@Slf4j
public class BeerControllerImpl implements BeerController {

    @Override
    public ResponseEntity<BeerDto> getBeerById(final UUID beerId) {
        //TODO implement!
        log.info("Getting by id {}", beerId);
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveNewBeer(final BeerDto beerDto) {
        //TODO implement
        log.info("Posting beer with name {}", beerDto.getBeerName());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateBeerById(final UUID beerId, final BeerDto beerDto) {
        //TODO implement
        log.info("updating beer with id {}", beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
