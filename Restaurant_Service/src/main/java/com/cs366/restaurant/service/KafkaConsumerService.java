package com.cs366.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cs366.restaurant.event.OrderDetailEvent;
import com.cs366.restaurant.model.OrderDetail;
import com.cs366.restaurant.model.OrderItem;
import com.cs366.restaurant.repository.OrderDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final KafkaProducerService kafkaProducerService;

    private final OrderDetailRepository detailRepository;

    @KafkaListener(topics = "orderpaid",groupId = "rest-group", containerFactory = "orderDetailKafkaListenerFactory")
    public void consume(OrderDetailEvent payload) {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setCustomerName(payload.getCustomerName());
        orderDetail.setNote(payload.getNote());
    
        List<OrderItem> os = new ArrayList<>();
        payload.getItems().forEach(o->{
            OrderItem ordetItem = new OrderItem();
            ordetItem.setMenuId(o.getMenuId());
            ordetItem.setMenuName(o.getMenuName());
            ordetItem.setQuantity(o.getQuantity());
            os.add(ordetItem);
        });

        orderDetail.setItems(os);
        detailRepository.save(orderDetail);

        kafkaProducerService.sendNotiChefEvent(payload);

    }
}
