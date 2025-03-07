package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisRepositoryTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void createTest(){
        Item item = Item.builder()
            .name("keyboard")
            .description("description")
            .price(100000)
            .build();
        itemRepository.save(item);
    }

    @Test
    public void readOneTest(){
        Item item = itemRepository.findById("2c67899d-e94a-4ce6-b80c-c9b7f9862737")
            .orElseThrow();
        System.out.println(item.getDescription());
    }

    @Test
    public void updateTest(){
        Item item = itemRepository.findById("2c67899d-e94a-4ce6-b80c-c9b7f9862737")
            .orElseThrow();
        item.setDescription("On sale!!!");
        item =  itemRepository.save(item);
        System.out.println(item.getDescription());
    }

    @Test
    public void deleteTest(){
        itemRepository.deleteById("2c67899d-e94a-4ce6-b80c-c9b7f9862737");
    }
}
