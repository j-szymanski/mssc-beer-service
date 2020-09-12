package prv.jws.microservices.beerservice.web.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import prv.jws.microservices.beerservice.services.BeerService;
import prv.jws.microservices.beerservice.web.controller.BeerController;
import prv.jws.microservices.beerservice.web.model.BeerDto;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BeerControllerImpl implements BeerController {

    private final BeerService beerService;

    @Override
    public ResponseEntity<BeerDto> getBeerById(final UUID beerId) {
        log.info("Getting by id {}", beerId);
        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveNewBeer(final BeerDto beerDto) {
        log.info("Posting beer with name {}", beerDto.getBeerName());
        beerService.saveNewBeer(beerDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateBeerById(final UUID beerId, final BeerDto beerDto) {
        log.info("updating beer with id {}", beerId);
        beerService.updateBeerById(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
