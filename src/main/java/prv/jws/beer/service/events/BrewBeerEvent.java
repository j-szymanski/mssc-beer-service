package prv.jws.beer.service.events;

import lombok.NoArgsConstructor;

import prv.jws.brewery.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
