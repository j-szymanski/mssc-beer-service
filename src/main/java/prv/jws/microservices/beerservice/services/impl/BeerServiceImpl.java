package prv.jws.microservices.beerservice.services.impl;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.stereotype.Service;
import prv.jws.microservices.beerservice.domain.Beer;
import prv.jws.microservices.beerservice.repositories.BeerRepository;
import prv.jws.microservices.beerservice.services.BeerService;
import prv.jws.microservices.beerservice.web.exceptions.NotFoundException;
import prv.jws.microservices.beerservice.web.mappers.BeerMapper;
import prv.jws.microservices.beerservice.web.model.BeerDto;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

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

        return beerMapper.beerToBeerDto(
                beerRepository.save(beer)
        );
    }
}
