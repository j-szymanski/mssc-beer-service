package prv.jws.beer.service.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import prv.jws.beer.service.config.JmsConfig;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.events.BrewBeerEvent;
import prv.jws.beer.service.events.NewInventoryEvent;
import prv.jws.beer.service.repositories.BeerRepository;
import prv.jws.beer.service.web.model.BeerDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewBeerListener {
    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listenForBrewingRequest(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        log.debug(">>>   Brewed beer {}/{} ", beer.getBeerName(), beer.getBeerStyle());
        log.debug(">>>   Min on hand {} quantity on hand {} ", beer.getMinOnHand(), beerDto.getQuantityOnHand());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
