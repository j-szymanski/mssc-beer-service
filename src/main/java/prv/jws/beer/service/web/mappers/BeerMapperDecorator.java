package prv.jws.beer.service.web.mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.services.inventory.BeerInventoryService;
import prv.jws.beer.service.web.model.BeerDto;

@Slf4j
public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;


    @Autowired
    public void setBeerInventoryService(final BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(final BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public Beer beerDtoToBeer(final BeerDto beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }

    @Override
    public BeerDto beerToBeerDto(final Beer beer) {
        BeerDto beerDto = beerMapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        log.debug("Beer Dto is {}",beerDto);
        return beerDto;
    }
}
