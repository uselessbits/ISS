package iss.flowershop;

import iss.flowershop.model.Flower;
import iss.flowershop.persistance.FlowerRepository;
import iss.flowershop.service.FlowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlowerServiceTests {

    @Mock
    private FlowerRepository flowerRepository;

    private FlowerService flowerService;

    @BeforeEach
    void setUp() {
        flowerService = new FlowerService();
    }


    @Test
    public void testAddFlowerTest(){
        Flower flower = new Flower("Rose", 10.0f, 5);
        when(flowerRepository.save(any(Flower.class))).thenReturn(flower);

        Flower savedFlower = flowerService.addFlower("Rose", 10.0f, 5);

        assertNotNull(savedFlower);
        assertEquals("Rose", savedFlower.getName());
        assertEquals(10.0f, savedFlower.getPrice());
        assertEquals(5, savedFlower.getQuantity());
        verify(flowerRepository).save(any(Flower.class));
    }
    
    @Test
    public void testGetFlowerTest(){
        Flower flower = new Flower("Rose", 10.0f, 5);
        flower.setId(1);
        when(flowerRepository.findById(1)).thenReturn(java.util.Optional.of(flower));

        Flower foundFlower = flowerService.getFlower(1);

        assertNotNull(foundFlower);
        assertEquals(1, foundFlower.getId());
        assertEquals("Rose", foundFlower.getName());
        verify(flowerRepository).findById(1);
    }
    @Test
    public void testUpdateFlowerTest(){
        Flower flower = new Flower("Rose", 10.0f, 5);
        flower.setId(1);
        when(flowerRepository.findById(1)).thenReturn(java.util.Optional.of(flower));
        when(flowerRepository.save(any(Flower.class))).thenReturn(flower);

        Flower updatedFlower = flowerService.updateFlower(1, 3);

        assertNotNull(updatedFlower);
        assertEquals(3, updatedFlower.getQuantity());
        verify(flowerRepository).findById(1);
        verify(flowerRepository).save(any(Flower.class));
    }
    @Test
    public void testDeleteFlowerTest(){
        Flower flower = new Flower("Rose", 10.0f, 5);
        flower.setId(1);
        when(flowerRepository.findById(1)).thenReturn(java.util.Optional.of(flower));
        doNothing().when(flowerRepository).deleteById(1);

        flowerService.deleteFlower(1);

        verify(flowerRepository).deleteById(1);
    }
}
