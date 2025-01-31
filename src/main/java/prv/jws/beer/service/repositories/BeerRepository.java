package prv.jws.beer.service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import prv.jws.beer.service.domain.Beer;
import prv.jws.brewery.model.BeerStyle;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, PageRequest pageRequest);

    Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, PageRequest pageRequest);

    Optional<Beer> findByUpc(String upc);
}
