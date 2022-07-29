package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by okostetskyi on 29.07.2022
 */
@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listenAllocation(AllocateOrderRequest allocateOrderRequest) {
        boolean isFullyAllocated = allocationService.allocateOrder(allocateOrderRequest.getBeerOrderDto());
    }
}
