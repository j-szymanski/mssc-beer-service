package prv.jws.beer.service.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.web.model.BeerStyle;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyle beerStyle, PageRequest pageRequest);

    Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);

    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, PageRequest pageRequest);
}
