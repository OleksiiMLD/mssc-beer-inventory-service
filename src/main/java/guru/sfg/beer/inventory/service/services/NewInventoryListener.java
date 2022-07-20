package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.events.NewInventoryEvent;
import guru.sfg.beer.inventory.service.web.model.BeerDto;
import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.sfg.beer.inventory.service.web.mappers.BeerInventoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by okostetskyi on 19.07.2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void handleNewInventoryEvent(NewInventoryEvent newInventoryEvent) {
        BeerDto beerDto = newInventoryEvent.getBeerDto();
        BeerInventory newInventory = beerInventoryMapper.beerDtoToBeerInventory(beerDto);
        beerInventoryRepository.save(newInventory);
    }
}
