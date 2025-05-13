package com.cs366.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cs366.order.event.OrderDetailEvent;
import com.cs366.order.event.OrderDetailEvent.OrderedItem;
import com.cs366.order.event.PaymentEvent;
import com.cs366.order.model.Order;
import com.cs366.order.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Component
public class OrderConsumer {

    private final OrderProducer producer;
    private final OrderRepository orderRepository;

    public OrderConsumer(OrderProducer producer, OrderRepository orderRepository) {
        this.producer = producer;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @KafkaListener(topics = "payment-events", containerFactory = "paymentKafkaListenerFactory")
    public void payment(PaymentEvent event) {
        Long orderId = (long) Integer.parseInt(event.getOrderId());
        Optional<Order> oorder = orderRepository.findById(orderId);

        if(oorder.isPresent()){
            Order upOrder = oorder.get();
            upOrder.setStatus("orderpaid");
            orderRepository.save(upOrder);

            OrderDetailEvent ode = new OrderDetailEvent();
            ode.setCustomerName(upOrder.getUserId());
            ode.setOrderId(String.valueOf(upOrder.getOrderId()));
            ode.setOrderedAt(upOrder.getCreatedAt());
            ode.setNote(upOrder.getNote());

            List<OrderedItem> items = new ArrayList<>();

            upOrder.getItems().stream().forEach(v->{
                OrderedItem oi = new OrderedItem(v.getMenuId(),"MenuNameMock",v.getQuantity());
                items.add(oi);

            });
            ode.setItems(items);

            producer.sendOrderDetail(ode);
            
        }else{
            System.out.println("Order " + orderId +" Not Found!!");
        }
    }
}
