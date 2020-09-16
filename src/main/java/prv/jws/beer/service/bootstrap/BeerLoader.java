package prv.jws.beer.service.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.repositories.BeerRepository;
import prv.jws.brewery.dto.BeerStyle;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {
    private final BeerRepository beerRepository;

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
//    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
//    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
//    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");
    @Override
    public void run(final String... args) throws Exception {
        if (beerRepository.count()==0){
            loadBeerObjects();
        }
    }

    private void loadBeerObjects() {
        if (beerRepository.count()== 0){
            beerRepository.save(Beer.builder()
                    .beerName("Żubr")
                    .beerStyle(BeerStyle.LAGER.name())
                    .quantityToBrew(200)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("5.80"))
                    .minOnHand(20)
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Królewskie IPA")
                    .beerStyle(BeerStyle.IPA.name())
                    .quantityToBrew(100)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("7.50"))
                    .minOnHand(30)
                    .build());
            beerRepository.save(Beer.builder()
                    .beerName("Irish Draft")
                    .beerStyle(BeerStyle.STOUT.name())
                    .quantityToBrew(500)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("8.50"))
                    .minOnHand(50)
                    .build());
            log.info("Beers Loaded! Number of beers: {}", beerRepository.count());
        }
    }
}
