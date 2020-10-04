package prv.jws.beer.service.services.inventory;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prv.jws.beer.service.services.inventory.model.BeerInventoryDto;

import static prv.jws.beer.service.services.inventory.impl.BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH;

/**
 * Created by Jerzy Szymanski on 04.10.2020 at 16:46
 */
@FeignClient(name="inventory-service")
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
