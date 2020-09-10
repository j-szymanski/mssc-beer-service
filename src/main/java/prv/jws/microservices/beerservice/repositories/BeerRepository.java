package prv.jws.microservices.beerservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import prv.jws.microservices.beerservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
