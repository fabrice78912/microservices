package com.fabricedev.orderserv.service;

import com.fabricedev.orderserv.dto.InventoryResponse;
import com.fabricedev.orderserv.dto.OrderLineItemsDto;
import com.fabricedev.orderserv.dto.OrderRequest;
import com.fabricedev.orderserv.model.Order;
import com.fabricedev.orderserv.model.OrderLineItems;
import com.fabricedev.orderserv.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClientService inventoryClientService;
    private final WebClient webClient;

    public void placedOrder(OrderRequest orderRequest){
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems= orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodeList = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        //Call Inventory Service and place order if product is in Stock

        List<InventoryResponse> inventoryResponseList = inventoryClientService.getInventoriesBySkuCode(skuCodeList);
        /*List<InventoryResponse inventoryResponseArray = webClient.get()
                .uri("http://localhost:8088/api/inventory" , uriBuilder -> uriBuilder.queryParam("skuCode" , skuCodeList).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();*/

        boolean allProductInStock = inventoryResponseList.stream() //Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductInStock){
            orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product is not in Stock, please try later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
