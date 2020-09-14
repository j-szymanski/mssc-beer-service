package prv.jws.beer.service.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getOnHandInventory(UUID beerId);
}
