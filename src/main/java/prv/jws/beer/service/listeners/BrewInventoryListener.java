package prv.jws.beer.service.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

import org.springframework.jms.annotation.JmsListener;
import prv.jws.beer.service.config.JmsConfig;
import prv.jws.brewery.events.NewInventoryEvent;
import prv.jws.brewery.model.BeerDto;

@Slf4j
//@Service
@RequiredArgsConstructor
public class BrewInventoryListener {
    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listenForBrewingRequest(NewInventoryEvent event) {
        BeerDto beerDto = event.getBeerDto();


        log.debug(">>>   Brewed beer {}/{} ", beerDto.getBeerName(), beerDto.getBeerStyle());
        log.debug(">>>   Quantity on hand {} ", beerDto.getQuantityOnHand());

    }
}
