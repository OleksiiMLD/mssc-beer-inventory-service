package guru.sfg.beer.inventory.service.services;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by okostetskyi on 29.07.2022
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listenAllocation(AllocateOrderRequest request) {
        final AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();

        try {
            boolean isFullyAllocated = allocationService.allocateOrder(request.getBeerOrderDto());

            builder.beerOrderDto(request.getBeerOrderDto());
            builder.pendingInventory(!isFullyAllocated);
        } catch (Exception e) {
            log.error("Allocation failed, Order Id:" + request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESULT_QUEUE, builder.build());
    }
}
