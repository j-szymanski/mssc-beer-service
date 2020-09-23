package prv.jws.brewery.events;

import lombok.NoArgsConstructor;

import prv.jws.brewery.model.BeerDto;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(final BeerDto beerDto) {
        super(beerDto);
    }
}
