package prv.jws.beer.service.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import prv.jws.beer.service.domain.Beer;
import prv.jws.beer.service.repositories.BeerRepository;
import prv.jws.beer.service.services.BeerService;
import prv.jws.beer.service.web.exceptions.NotFoundException;
import prv.jws.beer.service.web.mappers.BeerMapper;
import prv.jws.beer.service.web.model.BeerDto;
import prv.jws.beer.service.web.model.BeerPagedList;
import prv.jws.beer.service.web.model.BeerStyle;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerPagedList getPageOfBeers(final String beerName, final BeerStyle beerStyle, final PageRequest pageRequest) {
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
        list = new BeerPagedList(beerPage.getContent()
                .stream()
                .map(beer -> beerMapper.beerToBeerDto(beer))
                .collect(Collectors.toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
        return list;
    }

    @Override
    public BeerDto getById(final UUID beerId) {

        return beerMapper.beerToBeerDto(
                beerRepository
                        .findById(beerId)
                        .orElseThrow(NotFoundException::new));
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
