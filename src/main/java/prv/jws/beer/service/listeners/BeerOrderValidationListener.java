package prv.jws.beer.service.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import prv.jws.beer.service.config.JmsConfig;
import prv.jws.brewery.events.ValidateBeerOrderRequest;
import prv.jws.brewery.events.ValidateBeerOrderResponse;
import prv.jws.brewery.model.BeerOrderDto;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @Transactional
    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listenForOrderValidationRequest(ValidateBeerOrderRequest event) {
        BeerOrderDto orderDto = event.getBeerOrder();
        Boolean isValid = Optional.ofNullable(orderDto)
                                  .map(order -> beerOrderValidator.validateOrder(order.getBeerOrderLines()))
                                  .orElse(false);
        if (log.isDebugEnabled()) {
            if (isNull(orderDto)){
                log.error("!!!!!  >>>>>> ORDER IS NULL IN THE EVENT !!!!");
            }
            else {
                log.debug("Validating order {}, number of lines: {}", orderDto.getId(),
                        Optional.ofNullable(orderDto.getBeerOrderLines())
                                .map(List::size)
                                .orElse(-1));
            }
        }

        ValidateBeerOrderResponse response = ValidateBeerOrderResponse.builder()
                                                                      .orderId(orderDto.getId())
                                                                      .isValid(isValid)
                                                                      .build();
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, response);
    }
}
