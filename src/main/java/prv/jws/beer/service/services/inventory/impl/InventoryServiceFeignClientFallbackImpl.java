package prv.jws.beer.service.services.inventory.impl;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import prv.jws.beer.service.services.inventory.InventoryFailoverFeignClient;
import prv.jws.beer.service.services.inventory.InventoryServiceFeignClient;
import prv.jws.beer.service.services.inventory.model.BeerInventoryDto;

/**
 * Created by Jerzy Szymanski on 05.10.2020 at 10:33
 */
@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFallbackImpl implements InventoryServiceFeignClient {
    private final InventoryFailoverFeignClient feignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(final UUID beerId) {
        return feignClient.getOnHandInventory();
    }
}
