package prv.jws.beer.service.services.inventory;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import prv.jws.beer.service.services.inventory.model.BeerInventoryDto;

@FeignClient(name = "inventory-failover")
public interface InventoryFailoverFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();
}
