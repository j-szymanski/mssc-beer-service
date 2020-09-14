package prv.jws.beer.service.services.inventory.impl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prv.jws.beer.service.bootstrap.BeerLoader;
import prv.jws.beer.service.services.inventory.BeerInventoryService;

@SpringBootTest
@Slf4j
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {
        Integer qoh = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);
        log.info("Quantity on hand {}", qoh);
    }
}