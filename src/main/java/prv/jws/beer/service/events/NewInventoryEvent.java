package prv.jws.beer.service.events;

import prv.jws.beer.service.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
