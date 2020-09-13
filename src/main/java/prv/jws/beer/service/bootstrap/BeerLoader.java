package prv.jws.beer.service.bootstrap;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.repositories.BeerRepository;

@Slf4j
@Component
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

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
                    .beerStyle("Lager")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("5.80"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Królewskie IPA")
                    .beerStyle("IPA")
                    .quantityToBrew(100)
                    .minOnHand(10)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("7.50"))
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Irish Draft")
                    .beerStyle("Stout")
                    .quantityToBrew(500)
                    .minOnHand(30)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("8.50"))
                    .build());
            log.info("Beers Loaded! Number of beers: {}", beerRepository.count());
        }
    }
}
