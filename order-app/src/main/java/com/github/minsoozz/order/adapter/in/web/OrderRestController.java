package com.github.minsoozz.order.adapter.in.web;


import com.github.minsoozz.order.application.port.in.OrderCreateCommand;
import com.github.minsoozz.order.application.port.in.OrderUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderUseCase orderUseCase;

    public OrderRestController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderCreateCommand orderCreateCommand) {
        orderUseCase.createOrder(orderCreateCommand);
    }
}
