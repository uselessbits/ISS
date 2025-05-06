package iss.flowershop.service;


import iss.flowershop.model.Flower;
import iss.flowershop.persistance.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

@Service
public class FlowerService  {

    @Autowired
    private FlowerRepository flowerRepository;

    public void addFlower(iss.flowershop.model.Flower flower) {
        flowerRepository.save(flower);
    }
    public void deleteFlower(int id) {
        flowerRepository.deleteById(id);
    }
    public void deleteFlowerByName(String name) {
        List<Flower> flowers = flowerRepository.findAll();
        for (Flower flower : flowers) {
            if (flower.getName().equals(name)) {
                flowerRepository.delete(flower);
                break;
            }
        }
    }
    public Flower getFlower(int id) {
        return flowerRepository.findById(id).orElse(null);
    }
    public Map<Flower, Integer> getAllFlowers() {
        Map<String, Integer> flowerMap = new HashMap<>();
        Map<String, Flower> flowerByName = new HashMap<>();
        List<Flower> flowers = flowerRepository.findAll();

        for (Flower f : flowers) {
            String name = f.getName();
            flowerByName.put(name, f);
            Integer count = flowerMap.getOrDefault(name, 0);
            flowerMap.put(name, count + 1);
        }

        // Create a new map to store the result
        Map<Flower, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : flowerMap.entrySet()) {
            result.put(flowerByName.get(entry.getKey()), entry.getValue());
        }

        return result;
    }
    public void updateFlower(Integer id, int quantity) {
        Flower flower = flowerRepository.findById(id).orElse(null);
        if (flower != null) {
            flower.setQuantity(quantity);
            flowerRepository.save(flower);
        }
    }
}
