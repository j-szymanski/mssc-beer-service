package prv.jws.beer.service.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

import prv.jws.beer.service.web.model.BeerDto;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {
    static final long serialVersionUID = -4928064074737536983L;
    private final BeerDto beerDto;
}
