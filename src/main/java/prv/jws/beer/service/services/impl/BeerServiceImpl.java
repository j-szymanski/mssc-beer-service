package prv.jws.beer.service.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.repositories.BeerRepository;
import prv.jws.beer.service.services.BeerService;
import prv.jws.beer.service.web.exceptions.NotFoundException;
import prv.jws.beer.service.web.mappers.BeerMapper;
import prv.jws.brewery.model.BeerDto;
import prv.jws.beer.service.web.model.BeerPagedList;
import prv.jws.brewery.model.BeerStyle;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = {"beerListCache"}, condition = "#showOnHand == false")
    @Override
    public BeerPagedList getPageOfBeers(final String beerName, final BeerStyle beerStyle,
                                        final PageRequest pageRequest, final Boolean showOnHand) {

        log.debug("Getting beers");

        BeerPagedList list;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        }
        else if (!StringUtils.isEmpty(beerName)) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        }
        else if (!StringUtils.isEmpty(beerStyle)) {
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        }
        else {
            beerPage = beerRepository.findAll(pageRequest);
        }
        if (showOnHand){
            log.debug("Getting with inventory");
            list = new BeerPagedList(beerPage.getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }
        else {
            log.debug("Getting WITHOUT inventory");
            list = new BeerPagedList(beerPage.getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }
        return list;
    }

    @Cacheable(cacheNames = {"beerByUpcCache"}, key="#upc", condition = "#showOnHand == false")
    @Override
    public BeerDto getByUpc(final String upc, final Boolean showOnHand) {
        Beer beer = beerRepository
                .findByUpc(upc)
                .orElseThrow(NotFoundException::new);
        if (showOnHand){
            log.debug("Getting WITH inventory");
            return beerMapper.beerToBeerDtoWithInventory(beer);
        }
        else {
            log.debug("Getting WITHOUT inventory");
            return beerMapper.beerToBeerDto(beer);
        }
    }

    @Cacheable(cacheNames = {"beerCache"}, key="#beerId", condition = "#showOnHand == false")
    @Override
    public BeerDto getById(final UUID beerId, final Boolean showOnHand) {
        Beer beer = beerRepository
                .findById(beerId)
                .orElseThrow(NotFoundException::new);
        if (showOnHand){
            log.debug("Getting WITH inventory");
            return beerMapper.beerToBeerDtoWithInventory(beer);
        }
        else {
            log.debug("Getting WITHOUT inventory");
            return beerMapper.beerToBeerDto(beer);
        }
    }

    @Override
    public BeerDto saveNewBeer(final BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.save(
                        beerMapper.beerDtoToBeer(beerDto)
                )
        );
    }

    @Override
    public BeerDto updateBeerById(final UUID beerId, final BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer)
        );
    }
}
