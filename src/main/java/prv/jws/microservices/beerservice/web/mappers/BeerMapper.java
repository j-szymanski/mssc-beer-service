package prv.jws.microservices.beerservice.web.mappers;

import org.mapstruct.Mapper;
import prv.jws.microservices.beerservice.domain.Beer;
import prv.jws.microservices.beerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDto(Beer beer);
}
