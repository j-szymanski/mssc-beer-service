package prv.jws.beer.service.services.inventory.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import prv.jws.beer.service.services.inventory.BeerInventoryService;
import prv.jws.beer.service.services.inventory.InventoryServiceFeignClient;
import prv.jws.beer.service.services.inventory.model.BeerInventoryDto;

/**
 * Created by Jerzy Szymanski on 04.10.2020 at 16:52
 */
@Profile("local-discovery")
@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeignImpl implements BeerInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(final UUID beerId) {
        log.debug("Calling Inventory Feign Client for beer {}", beerId.toString());

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory( beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                                .stream()
                                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                                .sum();
        return onHand;
    }
}
