package com.example.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping
    public ItemOrder create(@RequestBody ItemOrder order) {
        return orderRepository.save(order);
    }

    @GetMapping("{id}")
    public ItemOrder getOrder(@PathVariable("id") String id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping //
    public List<ItemOrder> getAllOrders() {
        List<ItemOrder> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @PutMapping("{id}") //
    public ItemOrder update(
        @PathVariable("id") String id,
        @RequestBody ItemOrder order) {
        ItemOrder target = orderRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        target.setItem(order.getItem());
        target.setCount(order.getCount());
        target.setStatus(order.getStatus());
        target.setTotalPrice(order.getTotalPrice());

        return orderRepository.save(target);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable String id) {
        orderRepository.deleteById(id);
    }



}
