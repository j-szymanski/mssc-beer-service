package prv.jws.microservices.beerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<BeerDto> {
    public BeerPagedList(final List<BeerDto> content, final Pageable pageable, final long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(final List<BeerDto> content) {
        super(content);
    }
}
