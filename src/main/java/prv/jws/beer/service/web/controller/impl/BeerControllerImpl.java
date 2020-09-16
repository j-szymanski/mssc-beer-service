package prv.jws.beer.service.web.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import prv.jws.beer.service.services.BeerService;
import prv.jws.beer.service.web.controller.BeerController;
import prv.jws.beer.service.web.model.BeerPagedList;
import prv.jws.brewery.dto.BeerDto;
import prv.jws.brewery.dto.BeerStyle;

import static java.util.Objects.isNull;
import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@RestController
@Slf4j
public class BeerControllerImpl implements BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    private final BeerService beerService;

    @Override
    public ResponseEntity<BeerPagedList> getBeers(Integer pageNumber, Integer pageSize,
                                                  final String beerName, final BeerStyle beerStyle,
                                                  Boolean showOnHand) {
        log.debug("Getting all beers");
        log.debug(">> Requesting page number {}", pageNumber);
        log.debug(">> of page size {}", pageSize);
        log.debug(">> beer name like {}", beerName);
        log.debug(">> of style {}", isNull(beerStyle)?"not provided":beerStyle.name());
        log.debug(">> showOnHand: {}",isNull(showOnHand)?"not provided":showOnHand);
        if (isNull(showOnHand)){
            showOnHand = false;
        }
        if (isNull(pageNumber) || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (isNull(pageSize) || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        BeerPagedList pagedList = beerService.getPageOfBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showOnHand);
            return new ResponseEntity<>(pagedList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerDto> getBeerById(final UUID beerId,
                                               Boolean showOnHand) {
        if (isNull(showOnHand)){
            showOnHand = false;
        }
        log.info("Getting by id {}, show Inventory {}", beerId, showOnHand);
        return new ResponseEntity<>(beerService.getById(beerId, showOnHand), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BeerDto> getBeerByUpc(final String upc, Boolean showOnHand) {
        if (isNull(showOnHand)){
            showOnHand = false;
        }
        log.info("Getting by UPC {}, show Inventory {}", upc, showOnHand);
        return new ResponseEntity<>(beerService.getByUpc(upc, showOnHand), HttpStatus.OK);
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
