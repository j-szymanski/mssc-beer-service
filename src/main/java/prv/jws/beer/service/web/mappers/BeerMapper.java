package prv.jws.beer.service.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import prv.jws.beer.service.domain.Beer;
import prv.jws.brewery.model.BeerDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);
    BeerDto beerToBeerDtoWithInventory(Beer beer);
    BeerDto beerToBeerDto(Beer beer);
}
