package prv.jws.microservices.beerservice.bootstrap;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prv.jws.microservices.beerservice.domain.Beer;
import prv.jws.microservices.beerservice.repositories.BeerRepository;

@Slf4j
@Component
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public BeerLoader(final BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public void run(final String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count()== 0){
            beerRepository.save(Beer.builder()
                    .beerName("Żubr")
                    .beerStype("Lager")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(138909808L)
                    .price(new BigDecimal("5.80"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Królewskie IPA")
                    .beerStype("IPA")
                    .quantityToBrew(100)
                    .minOnHand(10)
                    .upc(138909801L)
                    .price(new BigDecimal("7.50"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Irish Draft")
                    .beerStype("Stout")
                    .quantityToBrew(500)
                    .minOnHand(30)
                    .upc(138908891L)
                    .price(new BigDecimal("8.50"))
                    .build());
            log.info("Beers Loaded! Number of beers: {}", beerRepository.count());
        }
    }
}
