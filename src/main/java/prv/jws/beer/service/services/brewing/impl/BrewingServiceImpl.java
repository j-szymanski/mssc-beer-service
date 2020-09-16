package prv.jws.beer.service.services.brewing.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.events.BrewBeerEvent;
import prv.jws.beer.service.repositories.BeerRepository;
import prv.jws.beer.service.services.brewing.BrewingService;
import prv.jws.beer.service.services.inventory.BeerInventoryService;
import prv.jws.beer.service.web.mappers.BeerMapper;

import static prv.jws.beer.service.config.JmsConfig.BREWING_REQUEST_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingServiceImpl implements BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Override
    @Scheduled(fixedRate = 5000)            //every 5 secs
    public void checkForLowInventory() {
        log.info("Checking for low inventory");
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer inventoryQOH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug(">>> Minimum on hand {}", beer.getMinOnHand());
            log.debug(">>> Inventory is: {}", inventoryQOH);

            if (beer.getMinOnHand() < inventoryQOH) {
                log.info("Time to brew {}/{} beer", beer.getBeerName(), beer.getBeerStyle());
                jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
