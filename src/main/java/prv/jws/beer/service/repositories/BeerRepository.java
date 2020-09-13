package prv.jws.beer.service.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import prv.jws.beer.service.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
